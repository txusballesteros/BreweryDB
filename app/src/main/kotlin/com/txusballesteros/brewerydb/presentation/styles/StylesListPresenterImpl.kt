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
package com.txusballesteros.brewerydb.presentation.styles

import com.txusballesteros.brewerydb.domain.model.BeersQuery
import com.txusballesteros.brewerydb.domain.usecase.beers.StoreBeersQueryUseCase
import com.txusballesteros.brewerydb.domain.usecase.styles.GetStylesUseCase
import com.txusballesteros.brewerydb.navigation.Navigator
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.StyleViewModel
import com.txusballesteros.brewerydb.presentation.model.StyleViewModelMapper
import javax.inject.Inject

class StylesListPresenterImpl @Inject constructor(private val getStylesUseCase: GetStylesUseCase,
                                                  private val storeBeersQueryUseCase: StoreBeersQueryUseCase,
                                                  private val styleMapper: StyleViewModelMapper,
                                                  private val navigator: Navigator):
                              AbsPresenter<StylesListPresenter.View>(), StylesListPresenter {

  override fun onRequestStyles() {
    val categoryId = getView()?.getCategoryId() ?: throw IllegalStateException("The StylesListPresenter.View is detached")
    getStylesUseCase.execute(categoryId, onResult = {
      val styles = styleMapper.map(it)
      getView()?.renderStyles(styles)
    })
  }

  override fun onStyleClick(style: StyleViewModel) {
    val query = BeersQuery(style.id)
    storeBeersQueryUseCase.execute (query, onResult = {
      navigator.navigateToBeersList(getView())
    })
  }
}
