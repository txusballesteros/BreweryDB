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

import com.txusballesteros.brewerydb.data.beers.datasource.BeerBreweriesCloudDataSource
import com.txusballesteros.brewerydb.data.beers.datasource.BeerBreweriesLocalDataSource
import com.txusballesteros.brewerydb.data.model.BreweryDataModel
import com.txusballesteros.brewerydb.data.strategy.LocalOrCloudStrategy
import javax.inject.Inject

class GetBeerBreweriesStrategy private constructor(private val localDataSource: BeerBreweriesLocalDataSource,
                                                   private val cloudDataSource: BeerBreweriesCloudDataSource):
                               LocalOrCloudStrategy<String, List<BreweryDataModel>>() {

  override fun onRequestCallToLocal(params: String?): List<BreweryDataModel>? {
    return localDataSource.get(params!!)
  }

  override fun onRequestCallToCloud(params: String?): List<BreweryDataModel>? {
    val response = cloudDataSource.get(params!!)
    localDataSource.store(params, response)
    return response
  }

  override fun isValid(result: List<BreweryDataModel>?): Boolean {
    return result != null && !result.isEmpty()
  }

  class Builder @Inject constructor(private val localDataSource: BeerBreweriesLocalDataSource,
                                    private val cloudDataSource: BeerBreweriesCloudDataSource) {
    fun build(): GetBeerBreweriesStrategy {
      return GetBeerBreweriesStrategy(localDataSource, cloudDataSource)
    }
  }
}