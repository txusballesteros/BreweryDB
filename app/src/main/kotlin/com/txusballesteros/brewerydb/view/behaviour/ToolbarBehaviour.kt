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
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.txusballesteros.brewerydb.R
import javax.inject.Inject

class ToolbarBehaviour @Inject constructor() : Behaviour() {
  lateinit var activity : AppCompatActivity

  override fun inject(activity: Activity) {
    if (activity is AppCompatActivity) {
      this.activity = activity
    }
    super.inject(activity)
  }

  override fun onRequestPlaceHolderId(): Int {
    return R.id.toolbar_place_holder
  }

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.toolbar_simple
  }

  override fun onBehaviorReady(view: View) {
    val toolbar = view.findViewById(R.id.toolbar) as Toolbar
    toolbar.title = "Brewery DB"
    toolbar.subtitle = "The final beer directory..."
    activity.setSupportActionBar(toolbar)
  }
}