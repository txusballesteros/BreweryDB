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
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.Toolbar
import android.view.View
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.instrumentation.ImageDownloader
import org.jetbrains.anko.find
import javax.inject.Inject

class ToolbarWithImageBehaviour @Inject constructor(private val imageDownloader: ImageDownloader) : Behaviour() {
  private lateinit var activity : AppCompatActivity
  private var enableBack: Boolean = false

  override fun inject(activity: Activity) {
    this.inject(activity, false)
  }

  fun inject(activity: Activity, enableBack: Boolean) {
    this.enableBack = enableBack
    if (activity is AppCompatActivity) {
      this.activity = activity
    }
    super.inject(activity)
  }

  override fun onRequestPlaceHolderId(): Int {
    return R.id.toolbar_place_holder
  }

  override fun onRequestBehaviourRootViewId(): Int {
    return R.id.collapsing_toolbar
  }

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.behaviour_toolbar_with_image
  }

  fun setTitle(title: String) {
    val collapsingToolbar = getView().find<CollapsingToolbarLayout>(R.id.collapsing_toolbar)
    collapsingToolbar.title = title
  }

  fun setImage(thumbnail: String? = null, image: String) {
    val imageView = getView().find<AppCompatImageView>(R.id.headerImage)
    imageDownloader.download(thumbnail, image, imageView)
  }

  override fun onBehaviorReady(view: View) {
    val toolbar = view.find<Toolbar>(R.id.toolbar)
    val appBarLayout = activity.find<AppBarLayout>(R.id.appBarLayout)
    val params = view.layoutParams as AppBarLayout.LayoutParams
    params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                         AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
    view.layoutParams = params
    activity.setSupportActionBar(toolbar)
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(enableBack)
    activity.supportActionBar?.setHomeButtonEnabled(enableBack)
    appBarLayout.fitsSystemWindows = true
  }
}