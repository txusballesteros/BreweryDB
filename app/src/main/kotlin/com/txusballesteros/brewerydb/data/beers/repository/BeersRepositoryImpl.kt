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
package com.txusballesteros.brewerydb.data.beers.repository

import com.txusballesteros.brewerydb.data.beers.strategy.GetBeersStrategy
import com.txusballesteros.brewerydb.data.model.BeerDataModel
import com.txusballesteros.brewerydb.data.model.BeerDataModelMapper
import com.txusballesteros.brewerydb.data.model.BeersQueryDataModelMapper
import com.txusballesteros.brewerydb.data.strategy.Strategy
import com.txusballesteros.brewerydb.domain.model.Beer
import com.txusballesteros.brewerydb.domain.model.BeersQuery
import com.txusballesteros.brewerydb.domain.repository.BeersRepository
import com.txusballesteros.brewerydb.domain.repository.Repository
import javax.inject.Inject

class BeersRepositoryImpl @Inject constructor(private val getBeersStrategy: GetBeersStrategy.Builder,
                                              private val mapper: BeerDataModelMapper,
                                              private val queryMapper: BeersQueryDataModelMapper): BeersRepository {
  override fun flush() {

  }

  override fun getBeers(query: BeersQuery, callback: Repository.RepositoryCallback<List<Beer>>) {
    val queryData = queryMapper.map(query)
    getBeersStrategy.build().execute(queryData, object: Strategy.Callback<List<BeerDataModel>>() {
      override fun onResult(result: List<BeerDataModel>?) {
        val beers = mapper.map(result!!)
        callback.onResult(beers)
      }
    })
  }
}