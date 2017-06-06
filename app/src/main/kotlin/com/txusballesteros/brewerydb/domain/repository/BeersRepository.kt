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
package com.txusballesteros.brewerydb.domain.repository

import com.txusballesteros.brewerydb.data.beers.strategy.*
import com.txusballesteros.brewerydb.data.model.BeerIngredient
import com.txusballesteros.brewerydb.data.model.mapToDomain
import com.txusballesteros.brewerydb.domain.model.Beer
import com.txusballesteros.brewerydb.domain.model.Brewery
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeersRepository @Inject constructor(private val getBeersStrategy: GetBeersStrategy.Builder,
                                          private val getNextPageBeersStrategy: GetNextPageBeersStrategy.Builder,
                                          private val getBeerByIdStrategy: GetBeerByIdStrategy.Builder,
                                          private val getBeerIngredientsStrategy: GetBeerIngredientsStrategy.Builder,
                                          private val getBeerBreweriesStrategy: GetBeerBreweriesStrategy.Builder) {

  fun getBreweries(beerId: String, onResult: (List<Brewery>) -> Unit) {
    getBeerBreweriesStrategy.build().execute(beerId, onResult = {
      val breweries = it.orEmpty().map { brewery -> mapToDomain(brewery) }
      onResult(breweries)
    })
  }

  fun getIngredients(beerId: String, onResult: (List<BeerIngredient>) -> Unit) {
    getBeerIngredientsStrategy.build().execute(beerId, onResult = {
      val ingredients = it.orEmpty().map { ingredient -> mapToDomain(ingredient) }
      onResult(ingredients)
    })
  }

  fun get(beerId: String, onResult: (Beer) -> Unit) {
    getBeerByIdStrategy.build().execute(beerId, onResult = {
      val beers = mapToDomain(it!!)
      onResult(beers)
    })
  }

  fun getFirstPage(onResult: (List<Beer>) -> Unit) {
    getBeersStrategy.build().execute(onResult = {
      val beers = it.orEmpty().map { beer ->  mapToDomain(beer) }
      onResult(beers)
    })
  }

  fun getNextPage(onResult: (List<Beer>) -> Unit) {
    getNextPageBeersStrategy.build().execute(onResult = {
      val beers = it.orEmpty().map { beer ->  mapToDomain(beer) }
      onResult(beers)
    })
  }
}