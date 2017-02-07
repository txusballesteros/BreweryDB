package com.txusballesteros.brewerydb.api.test

import com.txusballesteros.brewerydb.api.instrumentation.OkHttpRequestInterceptor
import okhttp3.OkHttpClient
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class ApiIntegrationTestGeneric {
  companion object {
    var BASE_URL : String = "http://api.brewerydb.com"
    val STATUS_SUCCESS : String = "success"
  }

  @Before
  fun onBefore() {
    val interceptor = OkHttpRequestInterceptor()
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    val converter = GsonConverterFactory.create()
    val retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(converter)
                        .client(client)
                        .build()
    onPrepareTest(retrofit)
  }

  abstract fun onPrepareTest(retrofit: Retrofit)
}