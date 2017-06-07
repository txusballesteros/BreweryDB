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

import com.txusballesteros.brewerydb.domain.usecase.beers.GetBeerByIdUseCase
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class BeerIbuPresenterImpl @Inject constructor(private val getBeerByIdUseCase: GetBeerByIdUseCase):
                           AbsPresenter<BeerIbuPresenter.View>(), BeerIbuPresenter {
  companion object {
    val UNKNOWN_IBU = 0f
  }

  override fun onRequestIbu(beerId: String) {
    launch(UI) {
      val result = getBeerByIdUseCase.execute(beerId)
      if (result.isRight()) {
        val value = string2float(result.right().get().ibu)
        getView()?.renderIbu(value)
      }
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