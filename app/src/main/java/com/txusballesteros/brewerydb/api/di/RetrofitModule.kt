package com.txusballesteros.brewerydb.api.di

import com.txusballesteros.brewerydb.api.beers.BeersRetrofitService
import com.txusballesteros.brewerydb.api.instrumentation.RequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {
  companion object {
    private var BASE_URL : String = "http://api.brewerydb.com/v2/"
  }

  @Provides
  fun provideBeersRetrofitService(retrofit: Retrofit) : BeersRetrofitService {
    return retrofit.create(BeersRetrofitService::class.java)
  }

  @Provides
  fun provideRestAdapter(converter: Converter.Factory,
                         client: OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(converter)
        .client(client)
        .build()
  }

  @Provides
  fun provideConverterFactory() : Converter.Factory {
    return GsonConverterFactory.create()
  }

  @Provides
  fun provideHttpClient(interceptor: Interceptor) : OkHttpClient {
    val client = OkHttpClient()
    client.interceptors().add(interceptor)
    return client
  }

  @Provides
  fun provideRequestInterceptor() : Interceptor {
    return RequestInterceptor()
  }
}