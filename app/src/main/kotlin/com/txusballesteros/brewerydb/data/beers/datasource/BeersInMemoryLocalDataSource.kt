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

import com.txusballesteros.brewerydb.data.model.BeerDataModel
import com.txusballesteros.brewerydb.data.model.BeersQueryDataModel
import java.util.*
import javax.inject.Inject

class BeersInMemoryLocalDataSource @Inject constructor(): BeersLocalDataSource {
  override fun flush() {
    cache.clear()
  }

  val cache: MutableMap<Int, List<BeerDataModel>> = HashMap()

  override fun store(query: BeersQueryDataModel, beers: List<BeerDataModel>) {
    if (!beers.isEmpty()) {
      cache.put(query.hashCode(), beers)
    }
  }

  override fun getBeers(query: BeersQueryDataModel): List<BeerDataModel> {
    return cache[query.hashCode()] ?: ArrayList()
  }
}