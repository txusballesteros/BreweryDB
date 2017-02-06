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
package com.txusballesteros.brewerydb.ui.behaviour

import android.app.Activity
import android.view.View
import android.view.ViewStub

abstract class Behaviour {
  open fun inject(activity: Activity) {
    inject(activity.window.decorView.rootView)
  }

  open fun inject(rootView: View) {
    val placeHolderView = findPlaceHolderView(rootView)
    attachBehaviorLayout(placeHolderView)
  }

  fun findPlaceHolderView(rootView: View) : ViewStub {
    val placeHolderId = onRequestPlaceHolderId()
    val result = rootView.findViewById(placeHolderId) as? ViewStub
        ?: throw IllegalArgumentException("Invalid behaviour place holder, that should be a ViewStub.")
    return result
  }

  fun attachBehaviorLayout(placeHolderView: ViewStub) {
    val layoutResourceId = onRequestLayoutResourceId()
    placeHolderView.layoutResource = layoutResourceId
    placeHolderView.setOnInflateListener { viewStub, view ->
      viewStub.visibility = View.VISIBLE
      onBehaviorReady(view)
    }
    placeHolderView.inflate()
  }

  abstract fun onRequestPlaceHolderId() : Int

  abstract fun onRequestLayoutResourceId() : Int

  abstract fun onBehaviorReady(view: View)
}