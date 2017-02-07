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
package com.txusballesteros.brewerydb.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.txusballesteros.brewerydb.Application
import com.txusballesteros.brewerydb.di.ApplicationComponent
import com.txusballesteros.brewerydb.view.di.DaggerViewComponent
import com.txusballesteros.brewerydb.view.di.ViewComponent

abstract class AbsFragment : Fragment() {
  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater?.inflate(onRequestLayoutResourceId(), container, false)
  }

  abstract fun onRequestLayoutResourceId() : Int

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    onRequestInjection()
    onRequestViewComposition()
    onViewReady(savedInstanceState)
  }

  private fun onRequestInjection() {
    val applicationComponent = getApplicationComponent()
    val viewComponent = DaggerViewComponent.builder().applicationComponent(applicationComponent)
                              .build()
    onRequestInjection(viewComponent)
  }

  abstract protected fun onRequestInjection(viewComponent: ViewComponent)

  open fun onRequestViewComposition() { }

  private fun getApplicationComponent() : ApplicationComponent {
    val application = activity.application as Application
    return application.applicationComponent
  }

  open fun onViewReady(savedInstanceState: Bundle?) { }
}