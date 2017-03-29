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
package com.txusballesteros.brewerydb.api.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.txusballesteros.brewerydb.api.instrumentation.OkHttpRequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RestModule {
  companion object {
    var BASE_URL : String = "http://api.brewerydb.com"
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
    return OkHttpClient.Builder()
              .addInterceptor(interceptor)
              .addNetworkInterceptor(StethoInterceptor())
              .build()
  }

  @Provides
  fun provideRequestInterceptor() : Interceptor {
    return OkHttpRequestInterceptor()
  }
}