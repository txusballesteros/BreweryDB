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
import android.view.View
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.domain.model.BeerViewModel
import com.txusballesteros.brewerydb.presentation.beers.BeerDetailPresenter
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.ErrorBehaviour
import com.txusballesteros.brewerydb.view.behaviours.LoadingBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_beer_detail.*
import kotlinx.android.synthetic.main.include_beer_detail_extra_info.*
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class BeerDetailFragment: AbsFragment(), BeerDetailPresenter.View {
  companion object {
    val EXTRA_BEER_ID = "extra:beerId"

    fun newInstance(beerId: String): BeerDetailFragment {
      return BeerDetailFragment().withArguments(
          EXTRA_BEER_ID to beerId
      )
    }
  }

  @Inject lateinit var presenter: BeerDetailPresenter
  @Inject lateinit var fragmentFactory: BeerDetailFragmentFactory
  @Inject lateinit var loadingBehaviour: LoadingBehaviour
  @Inject lateinit var errorBehaviour: ErrorBehaviour

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_beer_detail
  }

  override fun onPresenterShouldBeAttached() {
    presenter.onAttachView(this)
  }

  override fun onPresenterShouldBeDetached() {
    presenter.onDetachView()
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onRequestViewBehaviours() {
    loadingBehaviour.inject(activity)
    errorBehaviour.inject(activity, {
      requestBeer()
    })
  }

  override fun onComposeView() {
    val beerId =  getBeerId()
    val abvFragment = fragmentFactory.getAbvFragment(childFragmentManager, beerId)
    val ibuFragment = fragmentFactory.getIbuFragment(childFragmentManager, beerId)
    addFragment(R.id.abvPlaceHolder, abvFragment)
    addFragment(R.id.ibuPlaceHolder, ibuFragment)
  }

  private fun addFragment(containerView: Int, fragment: AbsFragment) {
    val tag = fragment.javaClass.name
    childFragmentManager
        .beginTransaction()
        .replace(containerView, fragment, tag)
        .commitAllowingStateLoss()
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    requestBeer()
  }

  private fun requestBeer() {
    val beerId = getBeerId()
    presenter.onRequestBeer(beerId)
  }

  private fun getBeerId(): String {
    return arguments.getString(EXTRA_BEER_ID)
  }

  override fun renderBeer(beer: BeerViewModel) {
    name.text = beer.name
    renderServingTemperature(beer)
    renderDescription(beer)
    renderIsOrganic(beer)
  }

  private fun renderDescription(beer: BeerViewModel) {
    description.visibility = beer.description?.let {
      description.text = it
      View.VISIBLE
    } ?: View.GONE
  }

  override fun renderGlass(glassName: String) {
    glass.text = glassName
  }

  override fun renderEmptyGlass() {
    glass.text = "NA"
  }

  private fun renderServingTemperature(beer: BeerViewModel) {
    temperature.text = beer.servingTemperatureDisplay
  }

  private fun renderIsOrganic(beer: BeerViewModel) {
    if (beer.isOrganic) {
      organic.setText(R.string.is_organic)
    } else {
      organic.setText(R.string.is_not_organic)
    }
  }

  override fun showLoading() {
    errorBehaviour.hideError()
    loadingBehaviour.showLoading()
  }

  override fun hideLoading() {
    loadingBehaviour.hideLoading()
  }

  override fun renderError() {
    errorBehaviour.showError()
  }
}