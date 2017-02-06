package com.txusballesteros.brewerydb.api.instrumentation

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
  companion object {
    private var API_KEY = "9741e805409497a470490a9e9a3908d8"
  }

  override fun intercept(chain: Interceptor.Chain?): Response {
    var request = chain!!.request()
    val url = request.url().newBuilder().addQueryParameter("key", API_KEY).build()
    request = request.newBuilder().url(url).build()
    return chain.proceed(request)
  }
}