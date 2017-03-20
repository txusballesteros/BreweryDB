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
package com.txusballesteros.brewerydb.data.beers.datasource

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.txusballesteros.brewerydb.UnitTest
import com.txusballesteros.brewerydb.api.beers.BeersApi
import com.txusballesteros.brewerydb.api.model.*
import com.txusballesteros.brewerydb.data.model.SearchQueryDataModel
import org.junit.Assert
import org.junit.Test
import java.util.*

class BeersCloudDataSourceTest: UnitTest() {
  companion object {
    private val BEER_ID = "L76Lnq"
    private val BEER_NAME = "Burton India Pale Ale"
    private val BEER_DISPLAY_NAME = "Burton India Pale Ale Display"
    private val BEER_DESCRIPTION = "This English Pale Ale is modeled after..."
    private val BEER_GLASSWARE_ID = 5
    private val BEER_ABV = "4.5"
    private val BEER_IBU = "15"
    private val BEER_IS_ORGANIC = "Y"
    private val BEER_STATUS = "verified"
    private val BEER_SERVING_TEMP = "5"
    private val BEER_SERVING_TEMP_DISPLAY = "5 C"
    private val BEER_LABEL = LabelApiModel("icon", "medium", "large")
    private val STYLE_ID = 2
    private val CURRENT_PAGE = 2
    private val QUERY = SearchQueryDataModel(null)
  }

  lateinit var api: BeersApi
  lateinit var dataSource: BeersCloudDataSource

  override fun onPrepareTest() {
    api = mock()
    dataSource = BeersRestCloudDataSource(api, BeerApiModelMapper(), SearchQueryApiModelMapper())
  }

  @Test
  fun shouldGetBeerById() {
    val beer = BeerApiModel(BEER_ID,
        BEER_NAME,
        BEER_DISPLAY_NAME,
        BEER_DESCRIPTION,
        STYLE_ID,
        BEER_ABV,
        BEER_IBU,
        BEER_GLASSWARE_ID,
        BEER_IS_ORGANIC,
        BEER_STATUS,
        BEER_LABEL,
        BEER_SERVING_TEMP,
        BEER_SERVING_TEMP_DISPLAY)
    val apiResponse = BeerApiResponse(beer, "Request Successful", "success")
    whenever(api.getBeerById(eq(BEER_ID))).thenReturn(apiResponse)

    val response = dataSource.getBeerById(BEER_ID)

    Assert.assertEquals(BEER_ID, response.id)
  }

  @Test
  fun shouldGetBeers() {
    val beers = ArrayList<BeerApiModel>()
    beers.add(BeerApiModel(BEER_ID,
                           BEER_NAME,
                           BEER_DISPLAY_NAME,
                           BEER_DESCRIPTION,
                           STYLE_ID,
                           BEER_ABV,
                           BEER_IBU,
                           BEER_GLASSWARE_ID,
                           BEER_IS_ORGANIC,
                           BEER_STATUS,
                           BEER_LABEL,
                           BEER_SERVING_TEMP,
                           BEER_SERVING_TEMP_DISPLAY))
    val apiResponse = BeersListApiResponse(beers, "Request Successful", "success", CURRENT_PAGE, CURRENT_PAGE, CURRENT_PAGE)
    whenever(api.getBeers(any())).thenReturn(apiResponse)

    val response = dataSource.getBeers(QUERY)

    Assert.assertFalse(response.isEmpty())
    Assert.assertEquals(BEER_ID, response.first().id)
    Assert.assertEquals(BEER_NAME, response.first().name)
    Assert.assertEquals(BEER_DISPLAY_NAME, response.first().displayName)
    Assert.assertEquals(BEER_DESCRIPTION, response.first().description)
    Assert.assertEquals(STYLE_ID, response.first().styleId)
    Assert.assertEquals(BEER_ABV, response.first().abv)
    Assert.assertEquals(BEER_IBU, response.first().ibu)
    Assert.assertEquals(BEER_GLASSWARE_ID, response.first().glasswareId)
    Assert.assertEquals(BEER_IS_ORGANIC, response.first().isOrganic)
    Assert.assertEquals(BEER_STATUS, response.first().status)
    Assert.assertEquals(BEER_SERVING_TEMP, response.first().servingTemperature)
    Assert.assertEquals(BEER_SERVING_TEMP_DISPLAY, response.first().servingTemperatureDisplay)
    Assert.assertEquals(BEER_LABEL.icon, response.first().label?.icon)
    Assert.assertEquals(BEER_LABEL.medium, response.first().label?.medium)
    Assert.assertEquals(BEER_LABEL.large, response.first().label?.large)
  }
}