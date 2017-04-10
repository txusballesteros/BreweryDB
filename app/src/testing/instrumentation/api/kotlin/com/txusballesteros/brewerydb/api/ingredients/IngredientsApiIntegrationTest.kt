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

import com.txusballesteros.brewerydb.api.ApiIntegrationTest
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit

class IngredientsApiIntegrationTest : ApiIntegrationTest() {
  companion object {
    val HOP_INGREDIENT_ID = 1
    val YEAST_INGREDIENT_ID = 1836
    val FERMENTABLE_INGREDIENT_ID = 166
  }

  lateinit var api: IngredientsApi

  override fun onPrepareTest(retrofit: Retrofit) {
    val hopService = retrofit.create(HopsRetrofitService::class.java)
    val yeastService = retrofit.create(YeastsRetrofitService::class.java)
    val fermentableService = retrofit.create(FermentableRetrofitService::class.java)
    api = IngredientsRetrofitApi(hopService, yeastService, fermentableService)
  }

  @Test
  fun shouldGetHop() {
    val response = api.getHop(HOP_INGREDIENT_ID)

    assertEquals(STATUS_SUCCESS, response.status)
    assertEquals(HOP_INGREDIENT_ID, response.ingredient.id)
  }

  @Test
  fun shouldGetYeast() {
    val response = api.getYeast(YEAST_INGREDIENT_ID)

    assertEquals(STATUS_SUCCESS, response.status)
    assertEquals(YEAST_INGREDIENT_ID, response.ingredient.id)
  }

  @Test
  fun shouldGetFermentable() {
    val response = api.getFermentable(FERMENTABLE_INGREDIENT_ID)

    assertEquals(STATUS_SUCCESS, response.status)
    assertEquals(FERMENTABLE_INGREDIENT_ID, response.ingredient.id)
  }
}