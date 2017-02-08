/*
 * Copyright Txus Ballesteros 2017 (@txusballesteros)
 *
 * This file is part of Foobar.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.brewerydb.view

import android.os.Bundle
import android.widget.Toast
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.model.StyleViewModel
import com.txusballesteros.brewerydb.presentation.styles.StylesListPresenter
import com.txusballesteros.brewerydb.view.behaviour.ToolbarBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import javax.inject.Inject

class MainFragment : AbsFragment(), StylesListPresenter.View {
  companion object {
    fun newInstance() : MainFragment {
      return MainFragment()
    }
  }
  @Inject lateinit var presenter : StylesListPresenter
  @Inject lateinit var toolbarBehaviour : ToolbarBehaviour

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_main
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onRequestViewComposition() {
    toolbarBehaviour.inject(activity as AbsActivity)
  }

  override fun onPresenterShouldBeAttached() {
    presenter.onAttachView(this)
  }

  override fun onPresenterShouldBeDetached() {
    presenter.onDetachView()
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    presenter.onRequestStyles()
  }

  override fun renderError() {
    Toast.makeText(activity, "Upps!! Fucking shit...", Toast.LENGTH_SHORT).show()
  }

  override fun renderStyles(styles: List<StyleViewModel>) {
    val name = styles.first().name
    Toast.makeText(activity, name, Toast.LENGTH_SHORT).show()
  }
}