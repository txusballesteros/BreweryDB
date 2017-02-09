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
package com.txusballesteros.brewerydb.view.styles

import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.Toast
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.model.StyleViewModel
import com.txusballesteros.brewerydb.presentation.styles.StylesListPresenter
import com.txusballesteros.brewerydb.view.AbsActivity
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviour.ToolbarBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_styles_list.*
import javax.inject.Inject

class StylesListFragment : AbsFragment(), StylesListPresenter.View {
  companion object {
    fun newInstance() : StylesListFragment {
      return StylesListFragment()
    }
  }
  @Inject lateinit var presenter: StylesListPresenter
  @Inject lateinit var toolbarBehaviour : ToolbarBehaviour
  val adapter: StyleListAdapter = StyleListAdapter()

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_styles_list
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
    initializeList()
    if (savedInstanceState == null) {
      presenter.onRequestStyles()
    }
  }

  private fun initializeList() {
    list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    list.adapter = adapter
    list.setHasFixedSize(true)
  }
  override fun renderStyles(styles: List<StyleViewModel>) {
    adapter.clear()
    adapter.addAll(styles)
    adapter.notifyDataSetChanged()
    Toast.makeText(activity, styles.first().name, Toast.LENGTH_SHORT).show()
  }

  override fun renderError() {
    Toast.makeText(activity, "Upps!!", Toast.LENGTH_SHORT).show()
  }
}