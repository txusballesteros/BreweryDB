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
package com.txusballesteros.brewerydb.domain.repository

import com.txusballesteros.brewerydb.data.ingredients.strategy.GetFermentableStrategy
import com.txusballesteros.brewerydb.data.ingredients.strategy.GetHopStrategy
import com.txusballesteros.brewerydb.data.ingredients.strategy.GetYeastStrategy
import com.txusballesteros.brewerydb.data.model.IngredientDataModelMapper
import com.txusballesteros.brewerydb.domain.model.Ingredient
import com.txusballesteros.brewerydb.domain.model.IngredientQuery
import com.txusballesteros.brewerydb.domain.model.IngredientType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IngredientsRepository @Inject constructor(private val getHopStrategy: GetHopStrategy.Builder,
                                                private val getFermentableStrategy: GetFermentableStrategy.Builder,
                                                private val getYeastStrategy: GetYeastStrategy.Builder,
                                                private val ingredientsMapper: IngredientDataModelMapper) {

  fun getIngredient(query: IngredientQuery, onResult: (Ingredient) -> Unit) {
    when(query.type) {
      IngredientType.HOP -> getHop(query, onResult)
      IngredientType.FERMENTABLE -> getFermentable(query, onResult)
      IngredientType.YEAST -> getYeast(query, onResult)
    }
  }

  fun getHop(query: IngredientQuery, onResult: (Ingredient) -> Unit) {
    getHopStrategy.build().execute(query.id, onResult = {
      val ingredient = ingredientsMapper.map(it!!)
      onResult(ingredient)
    })
  }

  fun getFermentable(query: IngredientQuery, onResult: (Ingredient) -> Unit) {
    getFermentableStrategy.build().execute(query.id, onResult = {
      val ingredient = ingredientsMapper.map(it!!)
      onResult(ingredient)
    })
  }

  fun getYeast(query: IngredientQuery, onResult: (Ingredient) -> Unit) {
    getYeastStrategy.build().execute(query.id, onResult = {
      val ingredient = ingredientsMapper.map(it!!)
      onResult(ingredient)
    })
  }
}