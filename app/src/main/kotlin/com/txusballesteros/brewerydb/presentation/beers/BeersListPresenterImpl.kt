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
package com.txusballesteros.brewerydb.presentation.beers

import com.txusballesteros.brewerydb.domain.model.BeerViewModel
import com.txusballesteros.brewerydb.domain.reactive.Observer
import com.txusballesteros.brewerydb.domain.usecase.beers.GetBeersUseCase
import com.txusballesteros.brewerydb.domain.usecase.beers.GetNextPageBeersUseCase
import com.txusballesteros.brewerydb.domain.usecase.search.ClearSearchQueryUseCase
import com.txusballesteros.brewerydb.domain.usecase.search.GetSearchQueryStreamUseCase
import com.txusballesteros.brewerydb.navigation.NavigationContext
import com.txusballesteros.brewerydb.navigation.Navigator
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.mapViewModel
import javax.inject.Inject

class BeersListPresenterImpl @Inject constructor(private val getBeersUseCase: GetBeersUseCase,
                                                 private val getNextPageBeersUseCase: GetNextPageBeersUseCase,
                                                 private val getSearchQueryStreamUseCase: GetSearchQueryStreamUseCase,
                                                 private val clearSearchQueryUseCase: ClearSearchQueryUseCase,
                                                 private val navigator: Navigator):
                              AbsPresenter<BeersListPresenter.View>(), BeersListPresenter {
  private lateinit var searchQueryObserver: Observer

  override fun onAttachView(view: BeersListPresenter.View) {
    super.onAttachView(view)
    getSearchQueryStreamUseCase.execute {
      this.searchQueryObserver = it
      this.searchQueryObserver.subscribe {
        onRequestBeers()
      }
    }
  }

  override fun onDetachView() {
    super.onDetachView()
    searchQueryObserver.unsubscribe()
  }

  override fun onRequestBeers() {
    getView()?.showLoading()
    getBeersUseCase.execute(onResult = {
      val beersList = it.map { beer -> mapViewModel(beer) }
      getView()?.clearList()
      getView()?.renderBeers(beersList)
      getView()?.hideLoading()
    }, onError = {
      getView()?.hideLoading()
      getView()?.renderError()
    })
  }

  override fun onRequestNextPage() {
    getNextPageBeersUseCase.execute(onResult = {
      val beersList = it.map { beer -> mapViewModel(beer) }
      getView()?.renderBeers(beersList)
    })
  }

  override fun onResetFilters() {
    clearSearchQueryUseCase.execute()
  }

  override fun onBeerClick(beer: BeerViewModel, view: android.view.View) {
    val navigationContext = NavigationContext.from(getView())
                                             .withSharedElements(view)
    navigator.navigateToBeerDetail(navigationContext, beer.id)
  }

  override fun onSearchClick() {
    navigator.navigateToSearch(NavigationContext.from(getView()))
  }

  override fun onAboutClick() {
    navigator.navigateToAbout(NavigationContext.from(getView()))
  }
}