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
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.extension.add
import com.txusballesteros.brewerydb.extension.find
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
    toolbarBehaviour.title = getString(R.string.search)
    toolbarBehaviour.inject(activity, true)
  }
  
  override fun onViewReady(savedInstanceState: Bundle?) {
    search.setOnClickListener { presenter.onSearch() }
  }

  override fun onComposeView() {
    addSection({ childFragmentManager.find<KeywordSearchSectionFragment>() ?: KeywordSearchSectionFragment.newInstance() }, R.id.keywordSearchHolder)
    addSection({ childFragmentManager.find<StyleSearchSectionFragment>() ?: StyleSearchSectionFragment.newInstance() }, R.id.styleSearchHolder)
    addSection({ childFragmentManager.find<AbvSearchSectionFragment>() ?: AbvSearchSectionFragment.newInstance() }, R.id.abvSearchHolder)
    addSection({ childFragmentManager.find<IbuSearchSectionFragment>() ?: IbuSearchSectionFragment.newInstance() }, R.id.ibuSearchHolder)
    addSection({ childFragmentManager.find<IsOrganicSearchSectionFragment>() ?: IsOrganicSearchSectionFragment.newInstance() }, R.id.isOrganicSearchHolder)
    addSection({ childFragmentManager.find<WithLabelsSearchSectionFragment>() ?: WithLabelsSearchSectionFragment.newInstance() }, R.id.withLabelSearchHolder)
  }

  private fun addSection(builder: () -> AbsFragment, holder: Int) {
    childFragmentManager.add(holder, builder)
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    inflater?.let {
       it.inflate(R.menu.menu_search, menu)
    }
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when(item?.itemId) {
      android.R.id.home -> consume { closeView() }
      R.id.action_clear -> consume { presenter.onClearFilters() }
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