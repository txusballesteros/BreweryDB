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

import com.txusballesteros.brewerydb.presentation.model.IngredientTypeViewModelMapper
import javax.inject.Inject

class BeerIngredientViewModelMapper @Inject constructor(private val typeMapper: IngredientTypeViewModelMapper) {
  fun map(source: List<BeerIngredient>): List<BeerIngredientViewModel>
    = source.map { ingredient -> map(ingredient) }

  fun map(source: BeerIngredient): BeerIngredientViewModel
    = BeerIngredientViewModel(source.id,
                              source.name,
                              typeMapper.map(source.type),
                              source.categoryDisplay)
}