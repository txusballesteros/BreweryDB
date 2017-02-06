package com.txusballesteros.brewerydb.api.beers

import com.txusballesteros.brewerydb.api.model.BeerApiModel
import retrofit2.http.GET

interface BeersRetrofitService {
  @GET("/beers")
  fun getBeers() : List<BeerApiModel>
}