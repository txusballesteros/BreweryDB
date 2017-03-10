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
import android.view.View
import com.txusballesteros.brewerydb.R
import org.jetbrains.anko.find
import javax.inject.Inject

class LoadingBehaviour @Inject constructor(): Behaviour() {
  lateinit var contentHolder: View
  lateinit var loadingHolder: View
  lateinit var activity : AppCompatActivity

  override fun inject(activity: Activity) {
    if (activity is AppCompatActivity) {
      this.activity = activity
    }
    super.inject(activity)
  }

  override fun onRequestPlaceHolderId(): Int {
    return R.id.loading_place_holder
  }

  override fun onRequestBehaviourRootViewId(): Int {
    return R.id.loadingHolder
  }

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.loading
  }

  fun showLoading() {
    contentHolder.visibility = View.GONE
    loadingHolder.visibility = View.VISIBLE
  }

  fun hideLoading() {
    contentHolder.visibility = View.VISIBLE
    loadingHolder.visibility = View.GONE
  }

  override fun onBehaviorReady(view: View) {
    this.loadingHolder = activity.find<View>(R.id.loadingHolder)
    this.contentHolder = activity.find<View>(R.id.content_place_holder)
  }
}