package com.txusballesteros.brewerydb.api.beers

import com.txusballesteros.brewerydb.api.model.BeerApiModel
import javax.inject.Inject

class BeersRetrofitApi : BeersApi {
  var service : BeersRetrofitService

  @Inject
  constructor(service: BeersRetrofitService) {
    this.service = service
  }

  override fun getBeers(): List<BeerApiModel> {
    return this.service.getBeers()
  }
}