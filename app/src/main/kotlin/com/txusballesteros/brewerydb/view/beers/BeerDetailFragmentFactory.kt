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
package com.txusballesteros.brewerydb.view.beers

import android.support.v4.app.FragmentManager
import javax.inject.Inject

class BeerDetailFragmentFactory @Inject constructor() {
  fun getAbvFragment(fragmentManager: FragmentManager, beerId: String ): BeerAbvFragment {
    val tag = BeerAbvFragment::class.java.name
    val fragment = fragmentManager.findFragmentByTag(tag) ?: BeerAbvFragment.newInstance(beerId)
    return fragment as BeerAbvFragment
  }

  fun getIbuFragment(fragmentManager: FragmentManager, beerId: String ): BeerIbuFragment {
    val tag = BeerIbuFragment::class.java.name
    val fragment = fragmentManager.findFragmentByTag(tag) ?: BeerIbuFragment.newInstance(beerId)
    return fragment as BeerIbuFragment
  }
}