package com.txusballesteros.brewerydb.api.di

import com.txusballesteros.brewerydb.api.beers.BeersRetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RetrofitModule {
  companion object {
    private var BASE_URL : String = "http://api.brewerydb.com/v2/"
    private var API_KEY = "9741e805409497a470490a9e9a3908d8"
  }

  @Provides
  fun provideBeersRetrofitService(retrofit: Retrofit) : BeersRetrofitService {
    return retrofit.create(BeersRetrofitService::class.java)
  }

  @Provides
  fun provideRestAdapter() : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build()
  }
}