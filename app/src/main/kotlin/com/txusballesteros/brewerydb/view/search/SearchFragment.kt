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
  @Inject lateinit var sectionsFragmentFactory: SearchSectionFragmentFactory

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
    toolbarBehaviour.title = getString(R.string.search)
    toolbarBehaviour.inject(activity, true)
  }
  
  override fun onViewReady(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      composeView()
    }
    search.setOnClickListener { presenter.onSearch() }
  }

  private fun composeView() {
    val keywordFragment = sectionsFragmentFactory.getKeywordSection(childFragmentManager)
    val styleFragment = sectionsFragmentFactory.getStyleSection(childFragmentManager)
    val abvFragment = sectionsFragmentFactory.getAbvSection(childFragmentManager)
    val ibuFragment = sectionsFragmentFactory.getIbuSection(childFragmentManager)
    val isOrganicFragment = sectionsFragmentFactory.getIsOrganicSection(childFragmentManager)
    addSection(keywordFragment, R.id.keywordSearchHolder)
    addSection(styleFragment, R.id.styleSearchHolder)
    addSection(abvFragment, R.id.abvSearchHolder)
    addSection(ibuFragment, R.id.ibuSearchHolder)
    addSection(isOrganicFragment, R.id.isOrganicSearchHolder)
  }

  private fun addSection(section: SearchSectionFragment, searchHolder: Int) {
    val tag = section::class.java.name
    childFragmentManager
        .beginTransaction()
        .replace(searchHolder, section, tag)
        .commitAllowingStateLoss()
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when(item?.itemId) {
      android.R.id.home -> consume { closeView() }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun getQuery(): SearchQueryViewModel {
    var result: SearchQueryViewModel = SearchQueryViewModel()
    childFragmentManager.fragments.forEach {
      if (it is SearchSectionFragment) {
        result = it.getQuery(result)
      }
    }
    return result
  }

  override fun closeView() {
    activity.finish()
  }
}