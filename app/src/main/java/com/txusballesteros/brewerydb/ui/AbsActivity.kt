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
package com.txusballesteros.brewerydb.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.txusballesteros.brewerydb.R

abstract class AbsActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base_layout)
    if (savedInstanceState == null) {
      addFragment()
    }
  }

  fun addFragment() {
    val fragment = onRequestFragment()
    supportFragmentManager.beginTransaction()
        .add(R.id.content_place_holder, fragment)
        .commit()
  }

  abstract fun onRequestFragment() : AbsFragment
}