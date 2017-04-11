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
import android.support.annotation.MenuRes
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.View
import com.txusballesteros.brewerydb.R
import org.jetbrains.anko.find
import javax.inject.Inject

class BottomNavigationBehaviour @Inject constructor(): Behaviour() {
  private var menuResourceId: Int = 0
  private lateinit var onItemSelected: (MenuItem) -> Boolean

  fun inject(activity: Activity, @MenuRes menuResourceId: Int, onItemSelected: (MenuItem) -> Boolean) {
    this.menuResourceId = menuResourceId
    this.onItemSelected = onItemSelected
    super.inject(activity)
  }

  override fun onRequestPlaceHolderId(): Int = R.id.bottom_navigation_place_holder

  override fun onRequestLayoutResourceId(): Int = R.layout.behaviour_bottom_navigation

  override fun onBehaviourReady(holder: View, view: View) {
    with(view.find<BottomNavigationView>(R.id.bottomNavigationMenu)) {
      inflateMenu(menuResourceId)
      setOnNavigationItemSelectedListener { menuItem -> onItemSelected(menuItem) }
    }
  }
}