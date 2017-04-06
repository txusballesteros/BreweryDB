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

import com.txusballesteros.brewerydb.domain.usecase.beers.GetBeerBreweriesUseCase
import com.txusballesteros.brewerydb.navigation.NavigationContext
import com.txusballesteros.brewerydb.navigation.Navigator
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.BreweryViewModel
import com.txusballesteros.brewerydb.presentation.model.BreweryViewModelMapper
import javax.inject.Inject

class BeerBreweriesPresenterImpl @Inject constructor(private val getBeerBreweriesUseCase: GetBeerBreweriesUseCase,
                                                     private val mapper: BreweryViewModelMapper,
                                                     private val navigator: Navigator):
                                 AbsPresenter<BeerBreweriesPresenter.View>(), BeerBreweriesPresenter {

  override fun onRequestBreweries(beerId: String) {
    getView()?.showLoading()
    getBeerBreweriesUseCase.execute(beerId, onResult = {
      val breweries = it.map { brewery -> mapper.map(brewery) }
      getView()?.hideLoading()
      getView()?.renderBreweries(breweries)
    }, onError = {
      getView()?.hideLoading()
      getView()?.showError()
    })
  }

  override fun onBreweryClick(brewery: BreweryViewModel) {
    navigator.navigateToBreweryDetail(NavigationContext.from(getView()), brewery.id)
  }
}