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
import android.support.v7.widget.StaggeredGridLayoutManager
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.domain.model.BeerViewModel
import com.txusballesteros.brewerydb.instrumentation.ImageDownloader
import com.txusballesteros.brewerydb.presentation.beers.BeersListPresenter
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviour.ToolbarBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_styles_list.*
import javax.inject.Inject

class BeersListFragment: AbsFragment(), BeersListPresenter.View {
  companion object {
    fun newInstance() = BeersListFragment()
  }

  @Inject lateinit var presenter: BeersListPresenter
  @Inject lateinit var toolbarBehaviour : ToolbarBehaviour
  @Inject lateinit var imageDownloader: ImageDownloader
  lateinit var adapter: BeerListAdapter

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

  override fun onRequestViewComposition() {
    toolbarBehaviour.inject(activity)
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    initializeList()
    presenter.onRequestBeers()
  }

  private fun initializeList() {
    adapter = BeerListAdapter(object: BeerListAdapter.OnBeerClickListener {
      override fun onBeerClick(beer: BeerViewModel) {

      }
    }, imageDownloader)
    list.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    list.adapter = adapter
    list.setHasFixedSize(true)
  }

  override fun renderBeers(beers: List<BeerViewModel>) {
    adapter.addAll(beers)
    adapter.notifyDataSetChanged()
  }
}