package com.txusballesteros.brewerydb.api.beers

import com.txusballesteros.brewerydb.api.model.BeerApiModel

interface BeersApi {
  fun getBeers() : List<BeerApiModel>
}