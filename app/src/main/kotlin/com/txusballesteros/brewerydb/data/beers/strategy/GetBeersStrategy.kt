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

import com.txusballesteros.brewerydb.data.beers.datasource.BeersCloudDataSource
import com.txusballesteros.brewerydb.data.beers.datasource.BeersLocalDataSource
import com.txusballesteros.brewerydb.data.model.BeerDataModel
import com.txusballesteros.brewerydb.data.model.BeersQueryDataModel
import com.txusballesteros.brewerydb.data.strategy.LocalOrCloudStrategy
import javax.inject.Inject

class GetBeersStrategy private constructor(private val localDataSource: BeersLocalDataSource,
                                           private val cloudDataSource: BeersCloudDataSource):
                       LocalOrCloudStrategy<BeersQueryDataModel, List<BeerDataModel>>() {

  override fun onRequestCallToLocal(query: BeersQueryDataModel?): List<BeerDataModel>? {
    return localDataSource.getBeers(query!!)
  }

  override fun onRequestCallToCloud(query: BeersQueryDataModel?): List<BeerDataModel>? {
    val response = cloudDataSource.getBeers(query!!)
    localDataSource.store(query, response)
    return response
  }

  override fun isValid(result: List<BeerDataModel>?): Boolean {
    return result != null && !result.isEmpty()
  }

  class Builder @Inject constructor(private val localDataSource: BeersLocalDataSource,
                                    private val cloudDataSource: BeersCloudDataSource) {
    fun build() = GetBeersStrategy(localDataSource, cloudDataSource)
  }
}