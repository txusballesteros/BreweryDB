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
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.domain.model.BeerViewModel
import com.txusballesteros.brewerydb.instrumentation.EndlessRecyclerViewScrollListener
import com.txusballesteros.brewerydb.instrumentation.ImageDownloader
import com.txusballesteros.brewerydb.presentation.beers.BeersListPresenter
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.ErrorBehaviour
import com.txusballesteros.brewerydb.view.behaviours.LoadingBehaviour
import com.txusballesteros.brewerydb.view.behaviours.ToolbarBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_styles_list.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class BeersListFragment: AbsFragment(), BeersListPresenter.View {
  companion object {
    fun newInstance(): BeersListFragment
        = BeersListFragment()
  }

  @Inject lateinit var presenter: BeersListPresenter
  @Inject lateinit var toolbarBehaviour : ToolbarBehaviour
  @Inject lateinit var loadingBehaviour: LoadingBehaviour
  @Inject lateinit var errorBehaviour: ErrorBehaviour
  @Inject lateinit var imageDownloader: ImageDownloader
  lateinit var adapter: BeerListAdapter
  lateinit var endlessScrollListener: EndlessRecyclerViewScrollListener

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_beers_list
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
    toolbarBehaviour.inject(activity)
    loadingBehaviour.inject(activity)
    errorBehaviour.inject(activity)
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    initializeList()
    presenter.onRequestBeers()
  }

  override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
    super.onCreateOptionsMenu(menu, inflater)
    inflater?.inflate(R.menu.menu_beer_list, menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    var result = true
    when(item?.itemId) {
      R.id.action_search -> presenter.onSearchClick()
      R.id.action_about -> presenter.onAboutClick()
      else -> result = super.onOptionsItemSelected(item)
    }
    return result
  }

  private fun initializeList() {
    val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    endlessScrollListener = object: EndlessRecyclerViewScrollListener(layoutManager) {
      override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
        presenter.onRequestNextPage()
      }
    }
    adapter = BeerListAdapter(object: BeerListAdapter.OnBeerClickListener {
      override fun onBeerClick(beer: BeerViewModel) {
        presenter.onBeerClick(beer)
      }
    }, imageDownloader)
    list.addOnScrollListener(endlessScrollListener)
    list.layoutManager = layoutManager
    list.adapter = adapter
    list.setHasFixedSize(true)
  }

  override fun clearList() {
    adapter.clear()
  }

  override fun renderBeers(beers: List<BeerViewModel>) {
    adapter.addAll(beers)
    adapter.notifyDataSetChanged()
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