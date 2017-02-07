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
package com.txusballesteros.brewerydb.api.instrumentation

import okhttp3.Interceptor
import okhttp3.Response

class OkHttpRequestInterceptor : Interceptor {
  companion object {
    private var API_KEY_QUERY_PARAM = "key"
    private var API_KEY = "9741e805409497a470490a9e9a3908d8"
  }

  override fun intercept(chain: Interceptor.Chain?): Response {
    var request = chain!!.request()
    val url = request.url().newBuilder().addQueryParameter(API_KEY_QUERY_PARAM, API_KEY).build()
    request = request.newBuilder().url(url).build()
    return chain.proceed(request)
  }
}