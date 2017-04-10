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
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.model.SearchQueryViewModel
import com.txusballesteros.brewerydb.presentation.search.SeachSectionPresenter
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_search_section_with_labels.*
import javax.inject.Inject

class WithLabelsSearchSectionFragment : SearchSectionFragment(), SeachSectionPresenter.View {
  companion object {
    fun newInstance(): WithLabelsSearchSectionFragment = WithLabelsSearchSectionFragment()
  }

  @Inject lateinit var presenter: SeachSectionPresenter

  override fun onRequestLayoutResourceId(): Int = R.layout.fragment_search_section_with_labels

  override fun onPresenterShouldBeAttached() {
    presenter.onAttachView(this)
  }

  override fun onPresenterShouldBeDetached() {
    presenter.onDetachView()
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      presenter.onRequestSearchQuery()
    }
  }

  override fun renderSearchQuery(query: SearchQueryViewModel) {
    query.withLabels?.let { withLabel.isChecked = query.withLabels }
  }

  override fun getQuery(source: SearchQueryViewModel): SearchQueryViewModel {
    var result = source;
    if (withLabel.isChecked) {
      result = source.copy(
          withLabels = withLabel.isChecked
      )
    }
    return result;
  }
}