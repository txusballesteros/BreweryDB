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
package com.txusballesteros.brewerydb.domain.usecase.beers

import com.txusballesteros.brewerydb.data.model.BeerIngredient
import com.txusballesteros.brewerydb.domain.repository.BeersRepository
import com.txusballesteros.brewerydb.domain.usecase.AnkoUseCase
import com.txusballesteros.brewerydb.exception.ApplicationException
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class GetBeerIngredientsInteractor @Inject constructor(executor: ExecutorService,
                                                       private val repository: BeersRepository):
                                    AnkoUseCase<List<BeerIngredient>>(executor), GetBeerIngredientsUseCase {
  lateinit var beerId: String

  override fun execute(beerId: String, onResult: (List<BeerIngredient>) -> Unit, onError: (ApplicationException) -> Unit) {
    this.beerId = beerId
    super.execute(onResult, onError)
  }


  override fun onExecute(onResult: (List<BeerIngredient>) -> Unit) {
    repository.getBeerIngredients(beerId, onResult = {
      onResult(it)
    })
  }
}