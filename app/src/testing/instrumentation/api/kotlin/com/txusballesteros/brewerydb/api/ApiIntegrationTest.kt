/*
 * Copyright Txus Ballesteros 2017 (@txusballesteros)
 *
 * This file is part of Foobar.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.brewerydb.api

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.txusballesteros.brewerydb.api.di.RestModule
import com.txusballesteros.brewerydb.api.instrumentation.OkHttpRequestInterceptor
import okhttp3.OkHttpClient
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class ApiIntegrationTest {
  companion object {
    val STATUS_SUCCESS : String = "success"
  }

  @Before
  fun onBefore() {
    val interceptor = OkHttpRequestInterceptor()
    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .addInterceptor(StethoInterceptor())
        .build()
    val converter = GsonConverterFactory.create()
    val retrofit = Retrofit.Builder()
                        .baseUrl(RestModule.BASE_URL)
                        .addConverterFactory(converter)
                        .client(client)
                        .build()
    onPrepareTest(retrofit)
  }

  abstract fun onPrepareTest(retrofit: Retrofit)
}