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

import com.txusballesteros.brewerydb.domain.model.SearchQuery
import com.txusballesteros.brewerydb.domain.usecase.search.GetSearchQueryUseCase
import com.txusballesteros.brewerydb.domain.usecase.search.StoreSearchQueryUseCase
import com.txusballesteros.brewerydb.navigation.Navigator
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.SearchQueryViewModelMapper
import javax.inject.Inject

class SearchPresenterImpl @Inject constructor(private val storeSearchQueryUseCase: StoreSearchQueryUseCase,
                                              private val getSearchQueryUseCase: GetSearchQueryUseCase,
                                              private val mapper: SearchQueryViewModelMapper,
                                              private val navigator: Navigator):
                          AbsPresenter<SearchPresenter.View>(), SearchPresenter {

  override fun onSearch() {
    val keyword = getView()?.getKeyword()
    val isOrganic = getView()?.getIsOrganic()
    val abvMin = getView()?.getAbvMin()
    val abvMax = getView()?.getAbvMax()
    val ibuMin = getView()?.getIbuMin()
    val ibuMax = getView()?.getIbuMax()
    val style = getView()?.getStyleId()
    val query = SearchQuery(keyword,
                            abvMin,
                            abvMax,
                            ibuMin,
                            ibuMax,
                            isOrganic,
                            null,
                            style)
    storeSearchQueryUseCase.execute(query, onResult = {
      getView()?.closeView()
    })
  }

  override fun onRequestFilters() {
    getSearchQueryUseCase.execute(onResult = {
      val query = mapper.map(it)
      getView()?.renderFilters(query)
    })
  }

  override fun onStyleSelectorClick() {
    navigator.navigateToStyleSelector(getView())
  }
}