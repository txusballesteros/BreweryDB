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
    private val EXTRA_CATEGORY_ID: String = "extra:categoryId"

    fun newInstance(categoryId: Int) : StylesListFragment {
      val arguments: Bundle = Bundle()
      arguments.putInt(EXTRA_CATEGORY_ID, categoryId)
      val result = StylesListFragment()
      result.arguments = arguments
      return result
    }
  }

  @Inject lateinit var presenter: StylesListPresenter
  @Inject lateinit var toolbarBehaviour : ToolbarBehaviour
  lateinit var adapter: StyleListAdapter

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_styles_list
  }

  override fun getCategoryId(): Int {
    return arguments.getInt(EXTRA_CATEGORY_ID)
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
    presenter.onRequestStyles()
  }

  private fun initializeList() {
    adapter = StyleListAdapter(object: StyleListAdapter.OnStyleClickListener {
      override fun onStyleClick(style: StyleViewModel) {
        Toast.makeText(activity, style.name, Toast.LENGTH_SHORT).show()
      }
    })
    list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    list.adapter = adapter
    list.setHasFixedSize(true)
  }

  override fun renderStyles(styles: List<StyleViewModel>) {
    adapter.clear()
    adapter.addAll(styles)
    adapter.notifyDataSetChanged()
  }

  override fun renderError(message: String) {
    Toast.makeText(activity, "Upps!! " + message, Toast.LENGTH_SHORT).show()
  }
}