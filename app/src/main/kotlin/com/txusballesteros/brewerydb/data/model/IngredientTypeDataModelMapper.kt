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
package com.txusballesteros.brewerydb.data.model

import com.txusballesteros.brewerydb.domain.model.IngredientType
import javax.inject.Inject

class IngredientTypeDataModelMapper @Inject constructor() {
  fun map(source: String): IngredientTypeDataModel {
    val result: IngredientTypeDataModel
    when(source) {
      "hop" -> result = IngredientTypeDataModel.HOP
      "yeast" -> result = IngredientTypeDataModel.YEAST
      "malt" -> result = IngredientTypeDataModel.FERMENTABLE
      else -> result = IngredientTypeDataModel.UNKNOWN
    }
    return result
  }

  fun map(source: IngredientTypeDataModel): IngredientType {
    val result: IngredientType
    when(source) {
      IngredientTypeDataModel.HOP -> result = IngredientType.HOP
      IngredientTypeDataModel.YEAST -> result = IngredientType.YEAST
      IngredientTypeDataModel.FERMENTABLE -> result = IngredientType.FERMENTABLE
      IngredientTypeDataModel.UNKNOWN -> result = IngredientType.UNKNOWN
    }
    return result
  }
}