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

import android.os.Bundle
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.beers.BeerAbvPresenter
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_beer_abv.*
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class BeerAbvFragment: AbsFragment(), BeerAbvPresenter.View {
  companion object {
    val EXTRA_BEER_ID = "extra:beerId"

    fun newInstance(beerId: String): BeerAbvFragment {
      return BeerAbvFragment().withArguments(
          EXTRA_BEER_ID to beerId
      )
    }
  }

  @Inject lateinit var presenter: BeerAbvPresenter

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_beer_abv
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onPresenterShouldBeAttached() {
    presenter.onAttachView(this)
  }

  override fun onPresenterShouldBeDetached() {
    presenter.onDetachView()
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    val beerId = getBeerId()
    presenter.onRequestAbv(beerId)
  }

  override fun renderAbv(value: Float) {
    abv.text = getString(R.string.abv_pattern, value)
  }

  private fun getBeerId(): String {
    return arguments.getString(EXTRA_BEER_ID)
  }
}