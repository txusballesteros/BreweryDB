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
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit

class HopsApiIntegrationTest: ApiIntegrationTest() {
  companion object {
    val INGREDIENT_ID = 1
  }

  lateinit var api: HopsApi

  override fun onPrepareTest(retrofit: Retrofit) {
    val service = retrofit.create(HopsRetrofitService::class.java)
    api = HopsRetrofitApi(service)
  }

  @Test
  fun shouldGetHop() {
    val response = api.getHop(INGREDIENT_ID)

    assertEquals(STATUS_SUCCESS, response.status)
    assertEquals(INGREDIENT_ID, response.ingredient.id)
  }
}