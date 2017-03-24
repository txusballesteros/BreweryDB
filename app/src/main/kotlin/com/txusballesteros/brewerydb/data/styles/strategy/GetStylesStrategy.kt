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
package com.txusballesteros.brewerydb.data.styles.strategy

import com.txusballesteros.brewerydb.data.model.StyleDataModel
import com.txusballesteros.brewerydb.data.strategy.LocalOrCloudStrategy
import com.txusballesteros.brewerydb.data.styles.datasource.StylesCloudDataSource
import com.txusballesteros.brewerydb.data.styles.datasource.StylesLocalDataSource
import javax.inject.Inject

class GetStylesStrategy constructor(val localDataSource: StylesLocalDataSource,
                                    val cloudDataSource: StylesCloudDataSource):
                        LocalOrCloudStrategy<Void, List<StyleDataModel>>() {

  override fun onRequestCallToLocal(params: Void?): List<StyleDataModel>? {
    return localDataSource.getList()
  }

  override fun onRequestCallToCloud(params: Void?): List<StyleDataModel>? {
    val response = cloudDataSource.getStyles()
    localDataSource.store(response)
    return localDataSource.getList()
  }

  override fun isValid(result: List<StyleDataModel>?): Boolean {
    return result != null && !result.isEmpty()
  }

  class Builder @Inject constructor(val localDataSource: StylesLocalDataSource,
                                    val cloudDataSource: StylesCloudDataSource) {
    fun build() : GetStylesStrategy {
      return GetStylesStrategy(localDataSource, cloudDataSource)
    }
  }
}