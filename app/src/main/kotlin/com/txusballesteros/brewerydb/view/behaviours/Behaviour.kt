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
package com.txusballesteros.brewerydb.view.behaviours

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.txusballesteros.brewerydb.extesion.inflate

abstract class Behaviour {
  lateinit var view: View
    private set

  open fun inject(activity: Activity) {
    inject(activity.window.decorView.rootView)
  }

  open fun inject(rootView: View) = findPlaceHolderView(rootView)?.apply {
    attachBehaviorLayout(this)
  }

  fun findPlaceHolderView(rootView: View) : ViewGroup? {
    val placeHolderId = onRequestPlaceHolderId()
    return rootView.findViewById(placeHolderId) as? ViewGroup
  }

  fun attachBehaviorLayout(placeHolderView: ViewGroup) {
    val layoutResourceId = onRequestLayoutResourceId()
    view = placeHolderView.inflate(layoutResourceId)
    placeHolderView.addView(view)
    onBehaviourReady(placeHolderView, view)
  }

  abstract fun onRequestPlaceHolderId() : Int

  abstract fun onRequestLayoutResourceId() : Int

  abstract fun onBehaviourReady(holder: View, view: View)
}