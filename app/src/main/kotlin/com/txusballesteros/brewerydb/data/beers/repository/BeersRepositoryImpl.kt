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

import com.txusballesteros.brewerydb.data.beers.strategy.GetBeerByIdStrategy
import com.txusballesteros.brewerydb.data.beers.strategy.GetBeersStrategy
import com.txusballesteros.brewerydb.data.beers.strategy.GetNextPageBeersStrategy
import com.txusballesteros.brewerydb.data.model.BeerDataModelMapper
import com.txusballesteros.brewerydb.domain.model.Beer
import com.txusballesteros.brewerydb.domain.repository.BeersRepository
import javax.inject.Inject

class BeersRepositoryImpl @Inject constructor(private val getBeersStrategy: GetBeersStrategy.Builder,
                                              private val getNextPageBeersStrategy: GetNextPageBeersStrategy.Builder,
                                              private val getBeerByIdStrategy: GetBeerByIdStrategy.Builder,
                                              private val mapper: BeerDataModelMapper): BeersRepository {

  override fun getBeerById(beerId: String, onResult: (Beer) -> Unit) {
    getBeerByIdStrategy.build().execute(beerId, onResult = {
      val beers = mapper.map(it!!)
      onResult(beers)
    })
  }

  override fun getBeers(onResult: (List<Beer>) -> Unit) {
    getBeersStrategy.build().execute(onResult = {
      val beers = mapper.map(it!!)
      onResult(beers)
    })
  }

  override fun getNextPageBeers(onResult: (List<Beer>) -> Unit) {
    getNextPageBeersStrategy.build().execute(onResult = {
      val beers = mapper.map(it!!)
      onResult(beers)
    })
  }
}