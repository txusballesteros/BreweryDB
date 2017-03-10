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
import org.jetbrains.anko.find

abstract class Behaviour {
  private lateinit var view: View

  open fun inject(activity: Activity) {
    inject(activity.window.decorView.rootView)
  }

  open fun inject(rootView: View) {
    val placeHolderView = findPlaceHolderView(rootView)
    if (placeHolderView != null) {
      attachBehaviorLayout(placeHolderView)
    } else {
      val behaviourView = rootView.find<View>(onRequestBehaviourRootViewId())
      onBehaviorReady(behaviourView)
    }
  }

  fun findPlaceHolderView(rootView: View) : ViewStub? {
    val placeHolderId = onRequestPlaceHolderId()
    val result = rootView.findViewById(placeHolderId) as? ViewStub
    return result
  }

  fun attachBehaviorLayout(placeHolderView: ViewStub) {
    val layoutResourceId = onRequestLayoutResourceId()
    placeHolderView.layoutResource = layoutResourceId
    placeHolderView.setOnInflateListener { viewStub, view ->
      this.view = view
      viewStub.visibility = View.VISIBLE
      onBehaviorReady(view)
    }
    placeHolderView.inflate()
  }

  protected fun getView(): View {
    return view
  }

  abstract fun onRequestPlaceHolderId() : Int

  abstract fun onRequestBehaviourRootViewId() : Int

  abstract fun onRequestLayoutResourceId() : Int

  abstract fun onBehaviorReady(view: View)
}