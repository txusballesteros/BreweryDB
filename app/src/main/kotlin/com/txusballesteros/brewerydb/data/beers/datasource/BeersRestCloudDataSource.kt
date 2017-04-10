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
import com.txusballesteros.brewerydb.api.model.SearchQueryApiModelMapper
import com.txusballesteros.brewerydb.data.model.BeerDataModel
import com.txusballesteros.brewerydb.data.model.SearchQueryDataModel
import javax.inject.Inject

class BeersRestCloudDataSource @Inject constructor(private val api: BeersApi,
                                                   private val mapper: BeerApiModelMapper,
                                                   private val queryMapper: SearchQueryApiModelMapper) : BeersCloudDataSource {

  override fun get(beerId: String): BeerDataModel {
    val response = api.get(beerId)
    return mapper.map(response.beer)
  }

  override fun getList(query: SearchQueryDataModel, page: Int): List<BeerDataModel> {
    val apiQuery = queryMapper.map(query)
    val response = api.getList(apiQuery, page)
    return response.beers.orEmpty().map { beer -> mapper.map(beer) }
  }
}