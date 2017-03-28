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
import android.support.v7.widget.LinearLayoutManager
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.beers.BeerBreweriesPresenter
import com.txusballesteros.brewerydb.presentation.model.BreweryViewModel
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.ErrorBehaviour
import com.txusballesteros.brewerydb.view.behaviours.LoadingBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_beer_breweries.*
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class BeerBreweriesFragment: AbsFragment(), BeerBreweriesPresenter.View {
  companion object {
    val EXTRA_BEER_ID = "extra:beerId"

    fun newInstance(beerId: String): BeerBreweriesFragment {
      return BeerBreweriesFragment().withArguments(
          EXTRA_BEER_ID to beerId
      )
    }
  }

  @Inject lateinit var presenter: BeerBreweriesPresenter
  @Inject lateinit var loadingBehaviour: LoadingBehaviour
  @Inject lateinit var errorBehaviour: ErrorBehaviour
  lateinit var adapter: BeerBreweriesAdapter

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_beer_breweries
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onRequestViewBehaviours() {
    loadingBehaviour.inject(activity)
    errorBehaviour.inject(activity, {
      requestBreweries()
    })
  }

  override fun onPresenterShouldBeAttached() {
    presenter.onAttachView(this)
  }

  override fun onPresenterShouldBeDetached() {
    presenter.onDetachView()
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    initializeList()
    requestBreweries()
  }

  private fun requestBreweries() {
    val beerId = getBeerId()
    presenter.onRequestBreweries(beerId)
  }

  private fun initializeList() {
    adapter = BeerBreweriesAdapter({
      presenter.onBreweryClick(it)
    })
    list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    list.setHasFixedSize(true)
    list.adapter = adapter
  }

  override fun renderBreweries(breweries: List<BreweryViewModel>) {
    adapter.clear()
    adapter.addAll(breweries)
    adapter.notifyDataSetChanged()
  }

  override fun showLoading() {
    errorBehaviour.hideError()
    loadingBehaviour.showLoading()
  }

  override fun hideLoading() {
    loadingBehaviour.hideLoading()
  }

  override fun showError() {
    errorBehaviour.showError()
  }

  private fun getBeerId(): String {
    return arguments.getString(EXTRA_BEER_ID)
  }
}