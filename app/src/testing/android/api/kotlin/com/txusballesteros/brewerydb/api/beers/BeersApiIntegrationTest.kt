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
import com.txusballesteros.brewerydb.api.model.BeersQueryApiModel
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit

class BeersApiIntegrationTest: ApiIntegrationTest() {
  companion object {
    private val STYLE_ID = 1
    private val BEER_ID = "2NLrMo"
  }

  lateinit var api : BeersApi

  override fun onPrepareTest(retrofit: Retrofit) {
    val service = retrofit.create(BeersRetrofitService::class.java)
    this.api = BeersRetrofitApi(service)
  }

  @Test
  fun shouldGetBeers() {
    val query = BeersQueryApiModel(STYLE_ID)

    val response = api.getBeers(query)

    Assert.assertEquals(STATUS_SUCCESS, response.status)
    Assert.assertFalse(response.beers.isEmpty())
  }

  @Test
  fun shouldGetBeerById() {
    val response = api.getBeerById(BEER_ID)

    Assert.assertEquals(STATUS_SUCCESS, response.status)
    Assert.assertEquals(BEER_ID, response.beer.id)
  }
}