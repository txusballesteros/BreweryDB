/*
 * Copyright Txus Ballesteros 2017 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.brewerydb.api.di

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
    private var BASE_URL : String = "http://api.brewerydb.com/v2/"
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
    return OkHttpRequestInterceptor()
  }
}