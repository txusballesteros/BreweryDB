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
package com.txusballesteros.brewerydb.api.ingredients

import com.txusballesteros.brewerydb.api.model.FermentableApiResponse
import com.txusballesteros.brewerydb.api.model.HopApiResponse
import com.txusballesteros.brewerydb.api.model.YeastApiResponse
import com.txusballesteros.brewerydb.exception.NetworkException
import javax.inject.Inject

class IngredientsRetrofitApi @Inject constructor(private val hopService: HopsRetrofitService,
                                                 private val yeastService: YeastsRetrofitService,
                                                 private val fermentableService: FermentableRetrofitService): IngredientsApi {

  override fun getHop(ingredientId: Int): HopApiResponse {
    try {
      val call = hopService.getIngredient(ingredientId)
      val response = call.execute()
      return response.body()
    } catch (error: Exception) {
      throw NetworkException(error)
    }
  }

  override fun getYeast(ingredientId: Int): YeastApiResponse {
    try {
      val call = yeastService.getIngredient(ingredientId)
      val response = call.execute()
      return response.body()
    } catch (error: Exception) {
      throw NetworkException(error)
    }
  }

  override fun getFermentable(ingredientId: Int): FermentableApiResponse {
    try {
      val call = fermentableService.getIngredient(ingredientId)
      val response = call.execute()
      return response.body()
    } catch (error: Exception) {
      throw NetworkException(error)
    }
  }
}