package com.txusballesteros.brewerydb.presentation.beers

import com.txusballesteros.brewerydb.presentation.Presenter

interface BeerIbuPresenter: Presenter<BeerIbuPresenter.View> {
  fun onRequestIbu(beerId: String)

  interface View: Presenter.View {
    fun renderIbu(min: Float, max: Float, value: Float)
  }
}