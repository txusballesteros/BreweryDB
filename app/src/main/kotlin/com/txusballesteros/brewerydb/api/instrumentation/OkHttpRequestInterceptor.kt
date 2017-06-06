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
package com.txusballesteros.brewerydb.api.instrumentation

import com.txusballesteros.brewerydb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class OkHttpRequestInterceptor : Interceptor {
  companion object {
    private var API_KEY_QUERY_PARAM = "key"
    private var API_KEY = BuildConfig.API_KEY
  }

  override fun intercept(chain: Interceptor.Chain?): Response {
    var request = chain!!.request()
    val url = request.url().newBuilder().addQueryParameter(API_KEY_QUERY_PARAM, API_KEY).build()
    request = request.newBuilder().url(url).build()
    return chain.proceed(request)
  }
}