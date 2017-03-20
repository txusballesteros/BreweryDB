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
package com.txusballesteros.brewerydb.view.search

import android.os.Bundle
import android.support.v7.widget.AppCompatEditText
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.model.SearchQueryViewModel
import com.txusballesteros.brewerydb.presentation.search.SearchPresenter
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.ToolbarBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment: AbsFragment(), SearchPresenter.View {
  companion object {
    fun newInstance(): SearchFragment {
      return SearchFragment()
    }
  }

  @Inject lateinit var toolbarBehaviour: ToolbarBehaviour
  @Inject lateinit var presenter: SearchPresenter

  override fun onRequestLayoutResourceId(): Int
    = R.layout.fragment_search

  override fun onPresenterShouldBeAttached() {
    presenter.onAttachView(this)
  }

  override fun onPresenterShouldBeDetached() {
    presenter.onDetachView()
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onRequestViewBehaviours() {
    toolbarBehaviour.inject(activity, true)
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      presenter.onRequestFilters()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.inflate(R.menu.menu_search, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    var result = true
    when(item?.itemId) {
      android.R.id.home -> closeView()
      R.id.action_done -> presenter.onSearch()
      else -> result = super.onOptionsItemSelected(item)
    }
    return result
  }

  override fun getKeyword(): String? {
    var keyword: String? = keyword.text.toString().trim()
    if (keyword != null && keyword.isEmpty()) {
      keyword = null
    }
    return keyword
  }

  override fun getIsOrganic(): Boolean {
    return isOrganic.isChecked
  }

  override fun getAbvMin(): Int? {
    return getRangeValue(abvMin)
  }

  override fun getAbvMax(): Int? {
    return getRangeValue(abvMax)
  }

  override fun getIbuMin(): Int? {
    return getRangeValue(ibuMin)
  }

  override fun getIbuMax(): Int? {
    return getRangeValue(ibuMax)
  }

  private fun getRangeValue(view: AppCompatEditText): Int? {
    var result: Int? = null
    var value: String = view.text.trim().toString()
    if (!value.isEmpty()) {
      result = value.toInt()
    }
    return result
  }

  override fun renderFilters(filter: SearchQueryViewModel) {
    keyword.setText(filter.keyword ?: "")
    isOrganic.isChecked = filter.isOrganic ?: false
    abvMin.setText(filter.abvMin?.toString() ?: "")
    abvMax.setText(filter.abvMax?.toString() ?: "")
    ibuMin.setText(filter.ibuMin?.toString() ?: "")
    ibuMax.setText(filter.ibuMax?.toString() ?: "")
  }

  override fun closeView() {
    activity.finish()
  }
}