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
package com.txusballesteros.brewerydb.api.model

import com.txusballesteros.brewerydb.data.model.BeerIngredientDataModel
import com.txusballesteros.brewerydb.data.model.IngredientTypeDataModelMapper
import javax.inject.Inject

class BeerIngredientApiModelMapper @Inject constructor(private val typeMapper: IngredientTypeDataModelMapper) {
  fun map(source: BeerIngredientsApiResponse): List<BeerIngredientDataModel>
    = map(source.ingredients)

  fun map(source: List<BeerIngredientApiModel>?): List<BeerIngredientDataModel>
    = source?.let {
      it.map { ingredient -> map(ingredient) }
    } ?: ArrayList()

  fun map(source: BeerIngredientApiModel): BeerIngredientDataModel
    = BeerIngredientDataModel(source.id,
                              source.name,
                              typeMapper.map(source.category),
                              source.categoryDisplay)
}