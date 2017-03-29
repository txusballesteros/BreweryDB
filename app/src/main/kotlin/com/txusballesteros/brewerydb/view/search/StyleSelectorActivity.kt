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
package com.txusballesteros.brewerydb.view.search

import com.txusballesteros.brewerydb.view.AbsActivity
import com.txusballesteros.brewerydb.view.AbsFragment

class StyleSelectorActivity: AbsActivity() {
  override fun onRequestFragment(): AbsFragment
    = StyleListSelectorFragment.newInstance()

  override fun onBackPressed() {
    getStyleListSelectorFragment()?.onBackPressed() ?: super.onBackPressed()
  }

  private fun getStyleListSelectorFragment(): StyleListSelectorFragment? {
    val tag = StyleListSelectorFragment::class.java.name
    return supportFragmentManager.findFragmentByTag(tag) as? StyleListSelectorFragment
  }
}