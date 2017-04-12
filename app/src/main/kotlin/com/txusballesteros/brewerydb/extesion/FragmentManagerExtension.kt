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
package com.txusballesteros.brewerydb.extesion

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.txusballesteros.brewerydb.view.AbsFragment

inline fun <reified T: Fragment>FragmentManager.find(): T? {
  val tag = T::class.java.name
  return findFragmentByTag(tag) as? T
}

fun FragmentManager.add(holder: Int, builder: () -> AbsFragment) {
  val fragment = builder()
  this.beginTransaction()
      .replace(holder, fragment, fragment::class.java.simpleName)
      .commitAllowingStateLoss()
}
