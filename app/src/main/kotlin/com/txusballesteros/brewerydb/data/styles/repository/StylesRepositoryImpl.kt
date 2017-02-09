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
package com.txusballesteros.brewerydb.data.styles.repository

import com.txusballesteros.brewerydb.data.model.StyleDataModel
import com.txusballesteros.brewerydb.data.model.map
import com.txusballesteros.brewerydb.data.strategy.Strategy
import com.txusballesteros.brewerydb.data.styles.strategy.GetStylesByCategoryIdStrategy
import com.txusballesteros.brewerydb.data.styles.strategy.GetStylesStrategy
import com.txusballesteros.brewerydb.domain.model.Style
import com.txusballesteros.brewerydb.domain.repository.Repository
import com.txusballesteros.brewerydb.domain.repository.StylesRepository
import javax.inject.Inject

class StylesRepositoryImpl @Inject constructor(private val getStylesStrategy: GetStylesStrategy.Builder,
                                               private val getStylesBuCategoryId: GetStylesByCategoryIdStrategy.Builder):
                                   StylesRepository {
  override fun getStylesByCategoryId(categoryId: Int, callback: Repository.RepositoryCallback<List<Style>>) {
    getStylesBuCategoryId.build().execute(categoryId, object: Strategy.Callback<List<StyleDataModel>>() {
      override fun onResult(result: List<StyleDataModel>?) {
        val styles = result?.map { style -> style.map(style) }
        callback.onResult(styles!!)
      }
    })
  }

  override fun getStyles(callback: Repository.RepositoryCallback<List<Style>>) {
    getStylesStrategy.build().execute(callback = object: Strategy.Callback<List<StyleDataModel>>() {
      override fun onResult(result: List<StyleDataModel>?) {
        val styles = result?.map { style -> style.map(style) }
        callback.onResult(styles!!)
      }
    })
  }
}