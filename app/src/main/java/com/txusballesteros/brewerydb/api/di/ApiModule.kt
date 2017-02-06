package com.txusballesteros.brewerydb.api.di

import com.txusballesteros.brewerydb.api.beers.BeersApi
import com.txusballesteros.brewerydb.api.beers.BeersRetrofitApi
import dagger.Module
import dagger.Provides

@Module
class ApiModule {
  @Provides
  fun provideBeersApi(api: BeersRetrofitApi) : BeersApi {
    return api;
  }
}