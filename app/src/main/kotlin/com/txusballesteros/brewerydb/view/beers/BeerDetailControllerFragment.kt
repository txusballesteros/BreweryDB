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
import com.txusballesteros.brewerydb.domain.model.BeerViewModel
import com.txusballesteros.brewerydb.presentation.beers.BeerDetailControllerPresenter
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviour.ToolbarWithImageBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_beer_detail.*
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class BeerDetailControllerFragment: AbsFragment(), BeerDetailControllerPresenter.View {
  companion object {
    val EXTRA_BEER_ID = "extra:beerId"

    fun newInstance(beerId: String): BeerDetailControllerFragment {
      return BeerDetailControllerFragment().withArguments(
          EXTRA_BEER_ID to beerId
      )
    }
  }

  @Inject lateinit var fragmentFactory: BeerDetailControllerFragmentFactory
  @Inject lateinit var toolbarBehaviour : ToolbarWithImageBehaviour
  @Inject lateinit var presenter: BeerDetailControllerPresenter

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_beer_detail_controller
  }

  override fun onPresenterShouldBeAttached() {
    presenter.onAttachView(this)
  }

  override fun onPresenterShouldBeDetached() {
    presenter.onDetachView()
  }

  override fun onComposeView() {
    val beerId = getBeerId()
    val beerDetailFragment = fragmentFactory.getBeerDetailFragment(childFragmentManager, beerId)
    addFragment(beerDetailFragment)
  }

  override fun onRequestViewComposition() {
    toolbarBehaviour.inject(activity)
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    val beerId = getBeerId()
    presenter.onRequestBeer(beerId)
  }

  private fun addFragment(fragment: AbsFragment) {
    val tag = fragment.javaClass.name
    childFragmentManager
        .beginTransaction()
        .replace(R.id.content, fragment, tag)
        .commitAllowingStateLoss()
  }

  override fun renderBeer(beer: BeerViewModel) {
    renderName(beer)
    renderLabel(beer)
  }

  private fun renderName(beer: BeerViewModel) {
    toolbarBehaviour.setTitle(beer.displayName)
    name.text = beer.displayName
  }

  private fun renderLabel(beer: BeerViewModel) {
    if (beer.label != null && beer.label.large != null) {
      toolbarBehaviour.setLabel(beer.label.large)
    }
  }

  private fun getBeerId(): String {
    return arguments.getString(EXTRA_BEER_ID)
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }
}