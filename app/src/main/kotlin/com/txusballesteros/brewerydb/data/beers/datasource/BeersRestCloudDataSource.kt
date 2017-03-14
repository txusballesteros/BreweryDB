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

import com.txusballesteros.brewerydb.api.beers.BeersApi
import com.txusballesteros.brewerydb.api.model.BeerApiModelMapper
import com.txusballesteros.brewerydb.api.model.BeersQueryApiModelMapper
import com.txusballesteros.brewerydb.data.model.BeerDataModel
import com.txusballesteros.brewerydb.data.model.BeersQueryDataModel
import javax.inject.Inject

class BeersRestCloudDataSource @Inject constructor(private val api: BeersApi,
                                                   private val mapper: BeerApiModelMapper,
                                                   private val queryMapper: BeersQueryApiModelMapper) : BeersCloudDataSource {
  override fun flush() {
    api.flush()
  }

  override fun getBeerById(beerId: String): BeerDataModel {
    val response = api.getBeerById(beerId)
    return mapper.map(response.beer)
  }

  override fun getBeers(query: BeersQueryDataModel): List<BeerDataModel> {
    val apiQuery = queryMapper.map(query)
    val response = api.getBeers(apiQuery)
    return mapper.map(response)
  }

  override fun getNextPageBeers(query: BeersQueryDataModel): List<BeerDataModel> {
    val apiQuery = queryMapper.map(query)
    val response = api.getNextPageBeers(apiQuery)
    return mapper.map(response)
  }
}