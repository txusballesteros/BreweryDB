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

import com.txusballesteros.brewerydb.data.model.BeerViewModelMapper
import com.txusballesteros.brewerydb.domain.model.BeerViewModel
import com.txusballesteros.brewerydb.domain.usecase.beers.GetBeerByIdUseCase
import com.txusballesteros.brewerydb.domain.usecase.styles.GetStyleByIdUseCase
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.StyleViewModel
import com.txusballesteros.brewerydb.presentation.model.StyleViewModelMapper
import javax.inject.Inject

class BeerDetailPresenterImpl @Inject constructor(private val getBeerByIdUseCase: GetBeerByIdUseCase,
                                                  private val getStyleByIdUseCase: GetStyleByIdUseCase,
                                                  private val mapper: BeerViewModelMapper,
                                                  private val styleMapper: StyleViewModelMapper):
                              AbsPresenter<BeerDetailPresenter.View>(), BeerDetailPresenter {
  companion object {
    val UNKNOWN_ABV = 0f
  }

  lateinit var beer: BeerViewModel

  override fun onRequestBeer(beerId: String) {
    getBeerByIdUseCase.execute(beerId, onResult = {
      beer = mapper.map(it)
      getStyle(beer.styleId)
      getView()?.renderBeer(beer)
    }, onError = {
      getView()?.renderError()
    })
  }

  private fun getStyle(styleId: Int?) {
    if (styleId != null) {
      getStyleByIdUseCase.execute(styleId, onResult = {
        val style = styleMapper.map(it)
        renderAbv(style)
      })
    }
  }

  fun renderAbv(style: StyleViewModel) {
    var min = UNKNOWN_ABV
    var max = UNKNOWN_ABV
    var value = UNKNOWN_ABV
    if (!style.abvMin.isEmpty()) {
      min = style.abvMin.toFloat()
    }
    if (!style.abvMax.isEmpty()) {
      max = style.abvMax.toFloat()
    }
    if (beer.abv != null) {
      value = beer.abv!!.toFloat()
    }
    getView()?.renderAbv(min, max, value)
  }
}