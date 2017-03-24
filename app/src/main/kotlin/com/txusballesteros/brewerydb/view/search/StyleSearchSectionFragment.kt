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
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.navigation.Navigator
import com.txusballesteros.brewerydb.navigation.RequestCodes
import com.txusballesteros.brewerydb.presentation.model.SearchQueryViewModel
import com.txusballesteros.brewerydb.presentation.search.SeachSectionPresenter
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_search_section_style.*
import javax.inject.Inject

class StyleSearchSectionFragment: SearchSectionFragment(), SeachSectionPresenter.View  {
  companion object {
    private val EXTRA_STYLE_ID: String = "extra:StyleId"
    private val EXTRA_STYLE_NAME: String = "extra:StyleName"

    fun newInstance() = StyleSearchSectionFragment()
  }

  @Inject lateinit var presenter: SeachSectionPresenter
  @Inject lateinit var navigator: Navigator
  private var styleId: Int? = null

  override fun onRequestLayoutResourceId(): Int
    = R.layout.fragment_search_section_style

  override fun onPresenterShouldBeAttached()
    = presenter.onAttachView(this)

  override fun onPresenterShouldBeDetached()
    = presenter.onDetachView()

  override fun onRequestInjection(viewComponent: ViewComponent)
    = viewComponent.inject(this)

  override fun onViewReady(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      presenter.onRequestSearchQuery()
    }
    style.setOnClickListener { navigator.navigateToStyleSelector(this) }
  }

  override fun onSaveInstanceState(outState: Bundle?) {
    if (styleId != null) {
      outState?.putInt(EXTRA_STYLE_ID, styleId as Int)
      outState?.putString(EXTRA_STYLE_NAME, style.text as String)
    }
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    savedInstanceState?.let {
      styleId = savedInstanceState.getInt(EXTRA_STYLE_ID)
      style.text = savedInstanceState.getString(EXTRA_STYLE_NAME)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    if (resultCode == Activity.RESULT_OK) {
      when(requestCode) {
        RequestCodes.STYLE_SELECTOR -> extractStyleFromIntent(data)
      }
    }
  }

  fun extractStyleFromIntent(data: Intent?) {
    data?.let {
      styleId = data.extras.getInt(StyleListSelectorFragment.EXTRA_STYLE_ID)
      style.text = data.extras.getString(StyleListSelectorFragment.EXTRA_STYLE_NAME)
    }
  }

  override fun getQuery(source: SearchQueryViewModel): SearchQueryViewModel {
    return styleId?.let {
      source.copy(styleId = styleId, styleName = style.text.toString())
    } ?: source
  }

  override fun renderSearchQuery(query: SearchQueryViewModel) {
    query.styleId?.let {
      styleId = query.styleId
      style.text = query.styleName
    }
  }
}