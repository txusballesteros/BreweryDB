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

import com.txusballesteros.brewerydb.data.model.Yeast
import com.txusballesteros.brewerydb.domain.model.Fermentable
import com.txusballesteros.brewerydb.domain.model.Hop
import com.txusballesteros.brewerydb.domain.model.Ingredient
import com.txusballesteros.brewerydb.domain.model.IngredientType
import javax.inject.Inject

class IngredientViewModelMapper @Inject constructor(private val hopMapper: HopViewModelMapper,
                                                    private val fermentableMapper: FermentableViewModelMapper,
                                                    private val yeastViewModelMapper: YeastViewModelMapper) {
  fun map(source: Ingredient): IngredientViewModel?
    = source.let {
        when(it.type) {
          IngredientType.HOP -> hopMapper.map(source as Hop)
          IngredientType.FERMENTABLE -> fermentableMapper.map(source as Fermentable)
          IngredientType.YEAST -> yeastViewModelMapper.map(source as Yeast)
          else -> null
        }
      }
}