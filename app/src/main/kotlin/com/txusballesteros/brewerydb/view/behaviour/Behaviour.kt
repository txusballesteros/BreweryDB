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
package com.txusballesteros.brewerydb.view.behaviour

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