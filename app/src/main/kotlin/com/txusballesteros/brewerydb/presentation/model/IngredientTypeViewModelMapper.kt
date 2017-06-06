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

fun mapToViewModel(source: IngredientType) = when(source) {
  IngredientType.HOP -> IngredientTypeViewModel.HOP
  IngredientType.YEAST ->  IngredientTypeViewModel.YEAST
  IngredientType.FERMENTABLE ->  IngredientTypeViewModel.FERMENTABLE
  IngredientType.UNKNOWN ->  IngredientTypeViewModel.UNKNOWN
}

fun mapToDomain(source: IngredientTypeViewModel) = when(source) {
  IngredientTypeViewModel.HOP -> IngredientType.HOP
  IngredientTypeViewModel.YEAST ->  IngredientType.YEAST
  IngredientTypeViewModel.FERMENTABLE ->  IngredientType.FERMENTABLE
  IngredientTypeViewModel.UNKNOWN ->  IngredientType.UNKNOWN
}