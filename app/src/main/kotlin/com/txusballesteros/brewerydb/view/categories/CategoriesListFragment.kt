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
package com.txusballesteros.brewerydb.view.categories

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.categories.CategoriesListPresenter
import com.txusballesteros.brewerydb.presentation.model.CategoryViewModel
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviour.ToolbarBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_styles_list.*
import javax.inject.Inject

class CategoriesListFragment: AbsFragment(), CategoriesListPresenter.View {
  companion object {
    fun newInstance(): CategoriesListFragment {
      return CategoriesListFragment()
    }
  }

  @Inject lateinit var toolbarBehaviour : ToolbarBehaviour
  @Inject lateinit var presenter: CategoriesListPresenter
  lateinit var adapter: CategoriesListAdapter

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_categories_list
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onRequestViewComposition() {
    toolbarBehaviour.inject(activity)
  }

  override fun onPresenterShouldBeAttached() {
    presenter.onAttachView(this)
  }

  override fun onPresenterShouldBeDetached() {
    presenter.onDetachView()
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    initializeList()
    presenter.onRequestCategories()
  }

  private fun initializeList() {
    adapter = CategoriesListAdapter(object: CategoriesListAdapter.OnCategoryClickListener {
      override fun onCategoryClick(category: CategoryViewModel) {
        presenter.onCategoryClick(category)
      }
    })
    list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    list.adapter = adapter
    list.setHasFixedSize(true)
  }

  override fun renderCategories(categories: List<CategoryViewModel>) {
    adapter.clear()
    adapter.addAll(categories)
    adapter.notifyDataSetChanged()
  }

  override fun renderError() { }
}