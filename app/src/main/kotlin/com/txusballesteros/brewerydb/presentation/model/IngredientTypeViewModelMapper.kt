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
package com.txusballesteros.brewerydb.presentation.model

import com.txusballesteros.brewerydb.domain.model.IngredientType
import javax.inject.Inject

class IngredientTypeViewModelMapper @Inject constructor() {
  fun map(source: String): IngredientTypeViewModel? {
    val result: IngredientTypeViewModel? = IngredientTypeViewModel.values().firstOrNull { it.value.equals(source) }
    return result
  }

  fun map(source: IngredientTypeViewModel): IngredientType? {
    var result: IngredientType? = null
    when(source) {
      IngredientTypeViewModel.HOP -> result = IngredientType.HOP
      IngredientTypeViewModel.YEAST -> result = IngredientType.YEAST
      IngredientTypeViewModel.MALT,
      IngredientTypeViewModel.MISCELLANEOUS -> result = IngredientType.FERMENTABLE
    }
    return result
  }
}