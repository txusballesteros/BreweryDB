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
    if (value != null) {
      result = value.toFloat()
    }
    return result
  }
}