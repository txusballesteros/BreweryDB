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
package com.txusballesteros.brewerydb.presentation.search

import com.txusballesteros.brewerydb.domain.usecase.search.ClearSearchQueryUseCase
import com.txusballesteros.brewerydb.domain.usecase.search.StoreSearchQueryUseCase
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.SearchQueryViewModel
import com.txusballesteros.brewerydb.presentation.model.SearchQueryViewModelMapper
import javax.inject.Inject

class SearchPresenterImpl @Inject constructor(private val storeSearchQueryUseCase: StoreSearchQueryUseCase,
                                              private val clearSearchQueryUseCase: ClearSearchQueryUseCase,
                                              private val mapper: SearchQueryViewModelMapper):
                          AbsPresenter<SearchPresenter.View>(), SearchPresenter {

  override fun onSearch() {
    val queryViewModel = getView()?.getQuery() ?: SearchQueryViewModel()
    val query = mapper.map(queryViewModel)
    storeSearchQueryUseCase.execute(query, onResult = {
      getView()?.closeView()
    })
  }

  override fun onClearFilters() {
    clearSearchQueryUseCase.execute {
      getView()?.closeView()
    }
  }
}