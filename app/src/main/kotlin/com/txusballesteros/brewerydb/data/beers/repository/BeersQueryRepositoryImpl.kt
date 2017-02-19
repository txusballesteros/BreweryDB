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

import com.txusballesteros.brewerydb.data.beers.strategy.GetBeersQueryStrategy
import com.txusballesteros.brewerydb.data.beers.strategy.StoreBeersQueryStrategy
import com.txusballesteros.brewerydb.data.model.BeersQueryDataModel
import com.txusballesteros.brewerydb.data.model.BeersQueryDataModelMapper
import com.txusballesteros.brewerydb.data.strategy.Strategy
import com.txusballesteros.brewerydb.domain.model.BeersQuery
import com.txusballesteros.brewerydb.domain.repository.BeersQueryRepository
import com.txusballesteros.brewerydb.domain.repository.Repository
import javax.inject.Inject

class BeersQueryRepositoryImpl @Inject constructor(private val getBeersQueryStrategy: GetBeersQueryStrategy.Builder,
                                                   private val storeBeersQueryStrategy: StoreBeersQueryStrategy.Builder,
                                                   private val mapper: BeersQueryDataModelMapper): BeersQueryRepository {
  override fun getQuery(callback: Repository.RepositoryCallback<BeersQuery>) {
    getBeersQueryStrategy.build().execute(callback = object: Strategy.Callback<BeersQueryDataModel>() {
      override fun onResult(result: BeersQueryDataModel?) {
        val query = mapper.map(result!!)
        callback.onResult(query)
      }
    })
  }

  override fun storeQuery(query: BeersQuery, callback: Repository.RepositoryEmptyCallback) {
    val dataQuery = mapper.map(query)
    storeBeersQueryStrategy.build().execute(dataQuery, object: Strategy.Callback<Void>() {
      override fun onResult(result: Void?) {
        callback.onResult()
      }
    })
  }
}