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

import com.txusballesteros.brewerydb.data.model.BeerIngredientViewModelMapper
import com.txusballesteros.brewerydb.domain.usecase.beers.GetBeerIngredientsUseCase
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import javax.inject.Inject

class BeerIngredientsPresenterImpl @Inject constructor(private val getBeerIngredientsUseCase: GetBeerIngredientsUseCase,
                                                       private val mapper: BeerIngredientViewModelMapper):
                                   AbsPresenter<BeerIngredientsPresenter.View>(), BeerIngredientsPresenter {
  override fun onRequestIngredients(beerId: String) {
    getView()?.showLoading()
    getBeerIngredientsUseCase.execute(beerId, onResult = {
      getView()?.hideLoading()
      val ingredients = mapper.map(it)
      getView()?.renderIngredients(ingredients)
    }, onError = {
      getView()?.hideLoading()
      getView()?.renderError()
    })
  }
}