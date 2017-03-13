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
import com.txusballesteros.brewerydb.presentation.beers.BeerDetailPresenter
import com.txusballesteros.brewerydb.presentation.model.StyleViewModel
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.LoadingBehaviour
import com.txusballesteros.brewerydb.view.behaviours.LoadingBehaviour_Factory
import com.txusballesteros.brewerydb.view.behaviours.ToolbarWithImageBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_beer_detail.*
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
  @Inject lateinit var loadingBehaviour: LoadingBehaviour

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
  }

  override fun onComposeView() {
    val abvFragment = getAbvFragment()
    val ibuFragment = getIbuFragment()
    addFragment(R.id.abvPlaceHolder, abvFragment)
    addFragment(R.id.ibuPlaceHolder, ibuFragment)
  }

  private fun getAbvFragment(): BeerAbvFragment {
    val tag = BeerAbvFragment::class.java.name
    var fragment = childFragmentManager.findFragmentByTag(tag) as BeerAbvFragment?
    if (fragment == null) {
      fragment = BeerAbvFragment.newInstance(getBeerId())
    }
    return fragment
  }

  private fun getIbuFragment(): BeerIbuFragment {
    val tag = BeerIbuFragment::class.java.name
    var fragment = childFragmentManager.findFragmentByTag(tag) as BeerIbuFragment?
    if (fragment == null) {
      fragment = BeerIbuFragment.newInstance(getBeerId())
    }
    return fragment
  }

  private fun addFragment(containerView: Int, fragment: AbsFragment) {
    val tag = fragment.javaClass.name
    childFragmentManager
        .beginTransaction()
        .replace(containerView, fragment, tag)
        .commitAllowingStateLoss()
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    val beerId = getBeerId()
    presenter.onRequestBeer(beerId)
  }

  private fun getBeerId(): String {
    return arguments.getString(EXTRA_BEER_ID)
  }

  override fun renderBeer(beer: BeerViewModel) {
    name.text = beer.name
    description.text = beer.description
    temperature.text = beer.servingTemperatureDisplay
    renderIsOrganic(beer)
  }

  override fun renderGlass(glassName: String) {
    glass.text = glassName
  }

  override fun renderEmptyGlass() {
    glass.text = "NA"
  }

  private fun renderIsOrganic(beer: BeerViewModel) {
    if (beer.isOrganic) {
      organic.setText(R.string.is_organic)
    } else {
      organic.setText(R.string.is_not_organic)
    }
  }

  override fun showLoading() {
    loadingBehaviour.showLoading()
  }

  override fun hideLoading() {
    loadingBehaviour.hideLoading()
  }

  override fun renderError() { }
}