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
package com.txusballesteros.brewerydb.api.styles

import com.txusballesteros.brewerydb.api.styles.StylesApi
import com.txusballesteros.brewerydb.api.styles.StylesRetrofitApi
import com.txusballesteros.brewerydb.api.styles.StylesRetrofitService
import com.txusballesteros.brewerydb.api.ApiIntegrationTest
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit

class StylesApiIntegrationTest : ApiIntegrationTest() {
  lateinit var api : StylesApi

  override fun onPrepareTest(retrofit: Retrofit) {
    val service = retrofit.create(StylesRetrofitService::class.java)
    this.api = StylesRetrofitApi(service)
  }

  @Test
  fun shouldGetStyles() {
    val response = api.getStyles()

    Assert.assertEquals(STATUS_SUCCESS, response.status)
  }
}