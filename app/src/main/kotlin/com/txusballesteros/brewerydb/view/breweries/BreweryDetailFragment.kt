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
package com.txusballesteros.brewerydb.view.breweries

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.breweries.BreweryDetailPresenter
import com.txusballesteros.brewerydb.presentation.model.BreweryViewModel
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.ErrorBehaviour
import com.txusballesteros.brewerydb.view.behaviours.LoadingBehaviour
import com.txusballesteros.brewerydb.view.behaviours.ToolbarWithImageBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_brewery_detail.*
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class BreweryDetailFragment: AbsFragment(), BreweryDetailPresenter.View {
  companion object {
    val EXTRA_BREWERY_ID = "extra:breweryId"

    fun newInstance(breweryId: String): BreweryDetailFragment {
      return BreweryDetailFragment().withArguments(
          EXTRA_BREWERY_ID to breweryId
      )
    }
  }

  @Inject lateinit var toolbarBehaviour : ToolbarWithImageBehaviour
  @Inject lateinit var loadingBehaviour: LoadingBehaviour
  @Inject lateinit var errorBehaviour: ErrorBehaviour
  @Inject lateinit var presenter: BreweryDetailPresenter

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_brewery_detail
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
    toolbarBehaviour.inject(activity, true)
    loadingBehaviour.inject(activity)
    errorBehaviour.inject(activity, {
      requestBreweries()
    })
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    website.setOnClickListener { presenter.onWebsiteClick() }
    requestBreweries()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    toolbarBehaviour.onSaveInstanceState(outState)
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    toolbarBehaviour.onViewStateRestored(savedInstanceState)
    super.onViewStateRestored(savedInstanceState)
  }

  private fun requestBreweries() {
    val breweryId = getBreweryId()
    presenter.onRequestBrewery(breweryId)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when(item!!.itemId) {
      android.R.id.home -> consume { closeView() }
      else -> super.onOptionsItemSelected(item)
    }
  }

  fun closeView() {
    activity.finish()
  }

  private fun getBreweryId(): String {
    return arguments.getString(EXTRA_BREWERY_ID)
  }

  override fun renderBrewery(brewery: BreweryViewModel) {
    name.text = brewery.name
    description.text = brewery.description
    toolbarBehaviour.setTitle(brewery.name)
    established.text = brewery.established
    renderWebsite(brewery)
    renderImage(brewery)
  }

  private fun renderWebsite(brewery: BreweryViewModel) {
    if (brewery.hasWebsite()) {
      website.text = brewery.website
      website.visibility = View.VISIBLE
    } else {
      website.visibility = View.GONE
    }
  }

  private fun renderImage(brewery: BreweryViewModel) {
    val image = brewery.largestImage()
    if (image != null) {
      toolbarBehaviour.setImage(thumbnail = brewery.thumbnailImage(), image = image)
    }
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
}