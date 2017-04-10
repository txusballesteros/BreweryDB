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

import com.txusballesteros.brewerydb.UnitTest
import com.txusballesteros.brewerydb.data.model.BeerDataModel
import org.junit.Assert
import org.junit.Test
import java.util.*

class BeersLocalDataSourceTest: UnitTest() {
  companion object {
    private val PAGE = 1
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
    private val BEER_LABEL = BeerDataModel.LabelDataModel("icon", "medium", "large")
    private val STYLE_ID = 2
  }

  lateinit var dataSource: BeersLocalDataSource

  override fun onPrepareTest() {
    dataSource = BeersInMemoryLocalDataSource()
  }

  @Test
  fun shouldStoreBeers() {
    val beers = ArrayList<BeerDataModel>()
    beers.add(BeerDataModel(BEER_ID,
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

    dataSource.store(beers, PAGE)
    val result = dataSource.getList()

    Assert.assertFalse(result.isEmpty())
    Assert.assertEquals(BEER_ID, result.first().id)
    Assert.assertEquals(BEER_NAME, result.first().name)
    Assert.assertEquals(BEER_DISPLAY_NAME, result.first().displayName)
    Assert.assertEquals(BEER_DESCRIPTION, result.first().description)
    Assert.assertEquals(STYLE_ID, result.first().styleId)
    Assert.assertEquals(BEER_ABV, result.first().abv)
    Assert.assertEquals(BEER_GLASSWARE_ID, result.first().glasswareId)
    Assert.assertEquals(BEER_IS_ORGANIC, result.first().isOrganic)
    Assert.assertEquals(BEER_STATUS, result.first().status)
    Assert.assertEquals(BEER_SERVING_TEMP, result.first().servingTemperature)
    Assert.assertEquals(BEER_LABEL.icon, result.first().label?.icon)
    Assert.assertEquals(BEER_LABEL.medium, result.first().label?.medium)
    Assert.assertEquals(BEER_LABEL.large, result.first().label?.large)
  }

  @Test
  fun shouldGetBeers() {
    val beers = ArrayList<BeerDataModel>()
    beers.add(BeerDataModel(BEER_ID,
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

    dataSource.store(beers, PAGE)
    val result = dataSource.getList()

    Assert.assertFalse(result.isEmpty())
    Assert.assertEquals(BEER_ID, result.first().id)
  }

  @Test
  fun shouldGetBeerById() {
    val beers = ArrayList<BeerDataModel>()
    beers.add(BeerDataModel(BEER_ID,
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

    dataSource.store(beers, PAGE)
    val result = dataSource.get(BEER_ID)

    Assert.assertNotNull(result)
    Assert.assertEquals(BEER_ID, result!!.id)
  }
}