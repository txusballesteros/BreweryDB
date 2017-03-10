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
package com.txusballesteros.brewerydb.presentation.ingredients

import com.txusballesteros.brewerydb.domain.model.IngredientType
import com.txusballesteros.brewerydb.domain.usecase.ingredients.GetIngredientUseCase
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.IngredientViewModelMapper
import javax.inject.Inject

class HopDetailPresenterImpl @Inject constructor(private val getIngredientUseCase: GetIngredientUseCase,
                                                 private val mapper: IngredientViewModelMapper):
                             AbsPresenter<HopDetailPresenter.View>(), HopDetailPresenter {

  override fun onRequestIngredient(ingredientId: Int) {
    getView()?.showLoading()
    getIngredientUseCase.execute(ingredientId, IngredientType.HOP, onResult = {
      val hop = mapper.map(it)
      getView()?.renderIngredient(hop)
      getView()?.hideLoading()
    }, onError = {
      getView()?.hideLoading()
      getView()?.renderError()
    })
  }
}