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
package com.txusballesteros.brewerydb.data.beers.strategy

import com.nhaarman.mockito_kotlin.*
import com.txusballesteros.brewerydb.UnitTest
import com.txusballesteros.brewerydb.data.beers.datasource.BeersCloudDataSource
import com.txusballesteros.brewerydb.data.beers.datasource.BeersLocalDataSource
import com.txusballesteros.brewerydb.data.beers.datasource.BeersQueryLocalDataSource
import com.txusballesteros.brewerydb.data.model.BeerDataModel
import com.txusballesteros.brewerydb.data.model.BeersQueryDataModel
import com.txusballesteros.brewerydb.data.strategy.Strategy
import org.junit.Assert
import org.junit.Test
import java.util.*

class GetBeersStrategyTest: UnitTest() {
  companion object {
    private val BEER_ID = "L76Lnq"
    private val BEER_NAME = "Burton India Pale Ale"
    private val BEER_DISPLAY_NAME = "Burton India Pale Ale Display"
    private val BEER_DESCRIPTION = "This English Pale Ale is modeled after..."
    private val BEER_GLASSWARE_ID = 5
    private val BEER_ABV = "4.5"
    private val BEER_IS_ORGANIC = "Y"
    private val BEER_STATUS = "verified"
    private val BEER_SERVING_TEMP = "5"
    private val BEER_LABEL = BeerDataModel.LabelDataModel("icon", "medium", "large")
    private val STYLE_ID = 2
    private val CURRENT_PAGE = 2
    private val QUERY = BeersQueryDataModel(STYLE_ID)
  }

  lateinit var queryLocalDataSource: BeersQueryLocalDataSource
  lateinit var localDataSource: BeersLocalDataSource
  lateinit var cloudDataSource: BeersCloudDataSource
  lateinit var query: BeersQueryDataModel
  lateinit var strategy: GetBeersStrategy

  override fun onPrepareTest() {
    localDataSource = mock()
    cloudDataSource = mock()
    queryLocalDataSource = mock()
    query = BeersQueryDataModel(1)
    strategy = GetBeersStrategy.Builder(queryLocalDataSource, localDataSource, cloudDataSource).build()
  }

  @Test
  fun shouldGetFromLocal() {
    val beers = ArrayList<BeerDataModel>()
    beers.add(BeerDataModel(BEER_ID,
                            BEER_NAME,
                            BEER_DISPLAY_NAME,
                            BEER_DESCRIPTION,
                            STYLE_ID,
                            BEER_ABV,
                            BEER_GLASSWARE_ID,
                            BEER_IS_ORGANIC,
                            BEER_STATUS,
                            BEER_LABEL,
                            BEER_SERVING_TEMP))
    whenever(localDataSource.getBeers()).thenReturn(beers)
    whenever(cloudDataSource.getBeers(any())).thenReturn(null)

    strategy.execute(callback = object: Strategy.Callback<List<BeerDataModel>>() {
      override fun onResult(result: List<BeerDataModel>?) {
        Assert.assertFalse(result!!.isEmpty())
        verify(localDataSource).getBeers()
        verify(cloudDataSource, never()).getBeers(any())
      }
    })
  }
}