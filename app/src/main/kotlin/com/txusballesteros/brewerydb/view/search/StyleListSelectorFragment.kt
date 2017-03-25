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
package com.txusballesteros.brewerydb.view.search

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.model.StyleViewModel
import com.txusballesteros.brewerydb.presentation.search.StyleListSelectorPresenter
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.ErrorBehaviour
import com.txusballesteros.brewerydb.view.behaviours.LoadingBehaviour
import com.txusballesteros.brewerydb.view.behaviours.ToolbarBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_styles_list_selector.*
import javax.inject.Inject

class StyleListSelectorFragment: AbsFragment(), StyleListSelectorPresenter.View {
  companion object {
    val EXTRA_STYLE_ID: String = "extra:StyleId"
    val EXTRA_STYLE_NAME: String = "extra:StyleName"

    fun newInstance(): StyleListSelectorFragment = StyleListSelectorFragment()
  }

  @Inject lateinit var presenter: StyleListSelectorPresenter
  @Inject lateinit var toolbarBehaviour: ToolbarBehaviour
  @Inject lateinit var loadingBehaviour: LoadingBehaviour
  @Inject lateinit var errorBehaviour: ErrorBehaviour
  private lateinit var adapter: StyleListAdapter

  override fun onRequestLayoutResourceId(): Int
    = R.layout.fragment_styles_list_selector

  override fun onPresenterShouldBeAttached()
    = presenter.onAttachView(this)

  override fun onPresenterShouldBeDetached()
    = presenter.onDetachView()

  override fun onRequestInjection(viewComponent: ViewComponent)
    = viewComponent.inject(this)

  override fun onRequestViewBehaviours() {
    toolbarBehaviour.inject(activity, true)
    loadingBehaviour.inject(activity)
    errorBehaviour.inject(activity, onRetry = {
      requestStyles()
    })
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean
    = when(item?.itemId) {
      android.R.id.home -> consume { closeView() }
      else -> super.onOptionsItemSelected(item)
    }

  override fun closeViewWithResult(style: StyleViewModel) {
    intent(EXTRA_STYLE_ID to style.id,
           EXTRA_STYLE_NAME to style.name).let {
      activity.setResult(Activity.RESULT_OK, it)
      activity.finish()
    }
  }

  private fun closeView() {
    activity.finish()
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    initializeList()
    requestStyles()
  }

  fun initializeList() {
    adapter = StyleListAdapter(onStyleSelected = {
      presenter.onStyleSelected(it)
    })
    list.adapter = adapter
    list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
  }

  private fun requestStyles()
    = presenter.onRequestStyles()

  override fun renderStyles(styles: List<StyleViewModel>) {
    adapter.clear()
    adapter.addAll(styles)
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
}