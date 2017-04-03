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
package com.txusballesteros.brewerydb.api.beers

import com.txusballesteros.brewerydb.api.ApiIntegrationTest
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit

class BeerIngredientsApiIntegrationTest : ApiIntegrationTest() {
  companion object {
    val BEER_ID = "WHQisc"
  }

  lateinit var api: BeerIngredientsApi

  override fun onPrepareTest(retrofit: Retrofit) {
    val service = retrofit.create(BeerIngredientsRetrofitService::class.java)
    api = BeerIngredientsRetrofitApi(service)
  }

  @Test
  fun shouldGetIngredients() {
    val response = api.getIngredients(BEER_ID)

    assertEquals(STATUS_SUCCESS, response.status)
  }
}