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
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.model.SearchQueryViewModel
import com.txusballesteros.brewerydb.presentation.search.SeachSectionPresenter
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_search_section_ibu.*
import javax.inject.Inject

class IbuSearchSectionFragment: SearchSectionFragment(), SeachSectionPresenter.View {
  companion object {
    fun newInstance(): IbuSearchSectionFragment = IbuSearchSectionFragment()
  }

  @Inject lateinit var presenter: SeachSectionPresenter

  override fun onRequestLayoutResourceId(): Int = R.layout.fragment_search_section_ibu

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
    query.ibuMin?.let { ibuMin.setText(it.toString()) }
    query.ibuMax?.let { ibuMax.setText(it.toString()) }
  }

  override fun getQuery(source: SearchQueryViewModel): SearchQueryViewModel {
    val minValue: Int? = getValueForRange(ibuMin)
    val maxValue: Int? = getValueForRange(ibuMax)
    return source.copy(
        ibuMin = minValue,
        ibuMax = maxValue
    )
  }

  private fun getValueForRange(view: AppCompatEditText): Int? {
    var result: Int? = null
    var value: String = view.text.trim().toString()
    if (!value.isEmpty()) {
      result = value.toInt()
    }
    return result
  }
}