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
package com.txusballesteros.brewerydb.presentation.breweries

import com.txusballesteros.brewerydb.domain.usecase.breweries.GetBreweryUseCase
import com.txusballesteros.brewerydb.navigation.NavigationContext
import com.txusballesteros.brewerydb.navigation.Navigator
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.BreweryViewModel
import com.txusballesteros.brewerydb.presentation.model.mapToViewModel
import javax.inject.Inject

class BreweryDetailPresenterImpl @Inject constructor(private val getBreweryUseCase: GetBreweryUseCase,
                                                     private val navigator: Navigator):
                                 AbsPresenter<BreweryDetailPresenter.View>(), BreweryDetailPresenter {
  lateinit var brewery: BreweryViewModel

  override fun onRequestBrewery(breweryId: String) {
    getView()?.showLoading()
    getBreweryUseCase.execute(breweryId, onResult = {
      this.brewery = mapToViewModel(it)
      getView()?.hideLoading()
      getView()?.renderBrewery(brewery)
    }, onError = {
      getView()?.hideLoading()
      getView()?.showError()
    })
  }

  override fun onWebsiteClick() {
    val website = brewery.website
    if (website != null) {
      navigator.navigateToUrl(NavigationContext.from(getView()), website)
    }
  }
}