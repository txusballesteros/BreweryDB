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

import com.txusballesteros.brewerydb.domain.usecase.ingredients.GetIngredientUseCase
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.*
import javax.inject.Inject

class IngredientDetailPresenterImpl @Inject constructor(private val getIngredientUseCase: GetIngredientUseCase):
                             AbsPresenter<IngredientDetailPresenter.View>(), IngredientDetailPresenter {

  override fun onRequestIngredient(ingredientId: Int, type: IngredientTypeViewModel) {
    getView()?.showLoading()
    val ingredientType = mapToDomain(type)
    getIngredientUseCase.execute(ingredientId, ingredientType, onResult = {
      val ingredient = mapToViewModel(it)
      if (ingredient != null) {
        getView()?.renderIngredient(ingredient)
      }
      getView()?.hideLoading()
    }, onError = {
      getView()?.hideLoading()
      getView()?.renderError()
    })
  }
}