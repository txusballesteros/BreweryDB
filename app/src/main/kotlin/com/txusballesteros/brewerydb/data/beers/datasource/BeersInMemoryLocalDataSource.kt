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
import java.util.*
import javax.inject.Inject

class BeersInMemoryLocalDataSource @Inject constructor(): BeersLocalDataSource {
  val cache: MutableMap<String, BeerDataModel> = HashMap()
  var page: Int = 1

  override fun flush() {
    cache.clear()
  }

  override fun store(beers: List<BeerDataModel>, page: Int) {
    if (!beers.isEmpty()) {

      beers.forEach { beer -> cache.put(beer.id, beer) }
    }
  }

  override fun store(beer: BeerDataModel) {
    cache.put(beer.id, beer)
  }

  override fun getList(): List<BeerDataModel> {
    return cache.values.toList().sortedBy { it.name }
  }

  override fun get(beerId: String): BeerDataModel? {
    return cache[beerId]
  }

  override fun getCurrentPage(): Int = page
}