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
package com.txusballesteros.brewerydb.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.txusballesteros.brewerydb.R

abstract class AbsActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val layoutResourceId = onRequestLayoutResourceId()
    setContentView(layoutResourceId)
    if (savedInstanceState == null) {
      addFragment()
    }
  }

  fun addFragment() {
    val fragment = onRequestFragment()
    supportFragmentManager.beginTransaction()
        .add(R.id.content_place_holder, fragment)
        .commit()
  }

  open protected fun onRequestLayoutResourceId(): Int {
    return R.layout.activity_coordinated_layout
  }

  abstract fun onRequestFragment() : AbsFragment
}