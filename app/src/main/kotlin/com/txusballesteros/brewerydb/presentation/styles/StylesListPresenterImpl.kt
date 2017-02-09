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
import com.txusballesteros.brewerydb.domain.model.map
import com.txusballesteros.brewerydb.domain.usecase.UseCase
import com.txusballesteros.brewerydb.domain.usecase.styles.GetStylesUseCase
import com.txusballesteros.brewerydb.exception.ApplicationException
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import javax.inject.Inject

class StylesListPresenterImpl @Inject constructor(private val getStylesUseCase: GetStylesUseCase)
                                            : AbsPresenter<StylesListPresenter.View>(), StylesListPresenter {

  override fun onRequestStyles() {
    getStylesUseCase.execute(object : UseCase.UseCaseCallback<List<Style>> {
      override fun onResult(styles: List<Style>) {
        val result = styles.map { style -> style.map(style) }
        getView()?.renderStyles(result)
      }

      override fun onError(error: ApplicationException) {
        getView()?.renderError(error.message.toString())
      }
    })
  }
}