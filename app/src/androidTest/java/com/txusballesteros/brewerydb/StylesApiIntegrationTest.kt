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
package com.txusballesteros.brewerydb

import com.txusballesteros.brewerydb.api.styles.StylesApi
import com.txusballesteros.brewerydb.api.styles.StylesRetrofitApi
import com.txusballesteros.brewerydb.api.styles.StylesRetrofitService
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit

class StylesApiIntegrationTest : ApiIntegrationTest() {
  private lateinit var api : StylesApi

  override fun onPrepareTest(retrofit: Retrofit) {
    val service = retrofit.create(StylesRetrofitService::class.java)
    this.api = StylesRetrofitApi(service)
  }

  @Test
  fun shouldGetStyles() {
    val response = api.getStyles()

    Assert.assertTrue(response.size > 0)
  }
}