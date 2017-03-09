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

import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviour.LoadingBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class BeerIngredientsFragment: AbsFragment() {
  companion object {
    val EXTRA_BEER_ID = "extra:beerId"

    fun newInstance(beerId: String): BeerIngredientsFragment {
      return BeerIngredientsFragment().withArguments(
          EXTRA_BEER_ID to beerId
      )
    }
  }

  @Inject lateinit var loadingBehaviour: LoadingBehaviour

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_beer_ingredients
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onPresenterShouldBeAttached() { }

  override fun onPresenterShouldBeDetached() { }

  override fun onRequestViewComposition() {
    loadingBehaviour.inject(activity)
  }
}