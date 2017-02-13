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

import com.txusballesteros.brewerydb.domain.model.Style
import com.txusballesteros.brewerydb.domain.usecase.UseCaseCallback
import com.txusballesteros.brewerydb.domain.usecase.styles.GetStylesUseCase
import com.txusballesteros.brewerydb.exception.ApplicationException
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.StyleViewModelMapper
import javax.inject.Inject

class StylesListPresenterImpl @Inject constructor(private val getStylesUseCase: GetStylesUseCase,
                                                  private val styleMapper: StyleViewModelMapper):
                              AbsPresenter<StylesListPresenter.View>(), StylesListPresenter {

  override fun onRequestStyles() {
      val categoryId = getView()?.getCategoryId() ?: throw IllegalStateException("The StylesListPresenter.View is detached")
      getStylesUseCase.execute(categoryId, object : UseCaseCallback<List<Style>>() {
        override fun onResult(result: List<Style>) {
          val styles = styleMapper.map(result)
          getView()?.renderStyles(styles)
        }

        override fun onError(error: ApplicationException) {
          getView()?.renderError()
        }
      })
  }
}
