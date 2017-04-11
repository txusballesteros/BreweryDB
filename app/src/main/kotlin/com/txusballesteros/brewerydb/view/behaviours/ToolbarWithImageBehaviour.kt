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

  override fun onRequestPlaceHolderId(): Int = R.id.toolbar_place_holder

  override fun onRequestLayoutResourceId(): Int = R.layout.behaviour_toolbar_with_image

  fun setTitle(title: String) = with(view.find<CollapsingToolbarLayout>(R.id.collapsing_toolbar)) {
    this.title = title
  }

  fun setImage(thumbnail: String? = null, image: String) = with(view.find<AppCompatImageView>(R.id.headerImage)) {
    imageDownloader.download(thumbnail, image, this)
  }

  override fun onBehaviourReady(holder: View, view: View) {
    val toolbar = view.find<Toolbar>(R.id.toolbar)
    with(activity) {
      setSupportActionBar(toolbar)
      supportActionBar?.setDisplayHomeAsUpEnabled(enableBack)
      supportActionBar?.setHomeButtonEnabled(enableBack)
    }
    holder.fitsSystemWindows = true
  }
}