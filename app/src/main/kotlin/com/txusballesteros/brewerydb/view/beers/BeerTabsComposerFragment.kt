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

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.domain.model.BeerViewModel
import com.txusballesteros.brewerydb.extension.add
import com.txusballesteros.brewerydb.extension.find
import com.txusballesteros.brewerydb.presentation.beers.BeerDetailControllerPresenter
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.BottomNavigationBehaviour
import com.txusballesteros.brewerydb.view.behaviours.ToolbarWithImageBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class BeerTabsComposerFragment : AbsFragment(), BeerDetailControllerPresenter.View {
  companion object {
    val EXTRA_BEER_ID = "extra:beerId"

    fun newInstance(beerId: String): BeerTabsComposerFragment {
      return BeerTabsComposerFragment().withArguments(
          EXTRA_BEER_ID to beerId
      )
    }
  }

  @Inject lateinit var toolbarBehaviour : ToolbarWithImageBehaviour
  @Inject lateinit var bottomNavigationBehaviour: BottomNavigationBehaviour
  @Inject lateinit var presenter: BeerDetailControllerPresenter

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_beer_tabs_composer
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

  override fun onRequestViewBehaviours() {
    toolbarBehaviour.inject(activity, true)
    bottomNavigationBehaviour.inject(activity, R.menu.bottom_navigation_beer_detail, {
      when(it.itemId) {
        R.id.action_beer_ingredients -> consume { presenter.onBeerIngredientsSelected() }
        R.id.action_beer_breweries -> consume { presenter.onBeerBreweriesSelected() }
        else -> consume { presenter.onBeerDetailSelected() }
      }
    })
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when(item?.itemId) {
      android.R.id.home -> consume { closeView() }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    toolbarBehaviour.onSaveInstanceState(outState)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    toolbarBehaviour.onViewStateRestored(savedInstanceState)
    super.onViewStateRestored(savedInstanceState)
  }

  private fun closeView() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      activity.finishAfterTransition()
    } else {
      activity.finish()
    }
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      presenter.onRequestBeer()
    }
  }

  override fun showBeerDetail() {
    val beerId = getBeerId()
    addFragment({ childFragmentManager.find<BeerDetailFragment>() ?: BeerDetailFragment.newInstance(beerId) })
  }

  override fun showBeerIngredients() {
    val beerId = getBeerId()
    addFragment({ childFragmentManager.find<BeerIngredientsFragment>() ?: BeerIngredientsFragment.newInstance(beerId) })
  }

  override fun showBeerBreweries() {
    val beerId = getBeerId()
    addFragment({ childFragmentManager.find<BeerBreweriesFragment>() ?: BeerBreweriesFragment.newInstance(beerId) })
  }

  private fun addFragment(builder: () -> AbsFragment) {
    childFragmentManager.add(R.id.content, builder)
  }

  override fun renderBeer(beer: BeerViewModel) {
    renderName(beer)
    renderLabel(beer)
  }

  private fun renderName(beer: BeerViewModel) {
    toolbarBehaviour.setTitle(beer.displayName)
  }

  private fun renderLabel(beer: BeerViewModel) {
    beer.label?.apply {
      beer.label.large?.apply {
        toolbarBehaviour.setImage(thumbnail = beer.label.medium,
                                  image =  beer.label.large)
      }
    }
  }

  override fun getBeerId(): String {
    return arguments.getString(EXTRA_BEER_ID)
  }
}