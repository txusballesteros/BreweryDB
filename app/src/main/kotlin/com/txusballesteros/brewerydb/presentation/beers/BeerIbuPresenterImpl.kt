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
package com.txusballesteros.brewerydb.presentation.beers

import com.txusballesteros.brewerydb.domain.model.Beer
import com.txusballesteros.brewerydb.domain.usecase.beers.GetBeerByIdUseCase
import com.txusballesteros.brewerydb.domain.usecase.styles.GetStyleByIdUseCase
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import javax.inject.Inject

class BeerIbuPresenterImpl @Inject constructor(private val getBeerByIdUseCase: GetBeerByIdUseCase,
                                               private val getStyleByIdUseCase: GetStyleByIdUseCase):
                           AbsPresenter<BeerIbuPresenter.View>(), BeerIbuPresenter {
  companion object {
    val UNKNOWN_IBU = 0f
  }

  override fun onRequestIbu(beerId: String) {
    getBeerByIdUseCase.execute(beerId, onResult = {
      getStyle(it)
    })
  }

  private fun getStyle(beer: Beer) {
    if (beer.styleId != null) {
      getStyleByIdUseCase.execute(beer.styleId, onResult = {
        val min = string2float(it.ibuMin)
        val max = string2float(it.ibuMax)
        val value = string2float(beer.ibu)
        getView()?.renderIbu(min, max, value)
      })
    }
  }

  private fun string2float(value: String?): Float {
    var result = UNKNOWN_IBU
    if (value != null && !value.isEmpty()) {
      result = value.toFloat()
    }
    return result
  }
}