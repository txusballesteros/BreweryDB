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
import com.txusballesteros.brewerydb.data.strategy.LocalStrategy
import com.txusballesteros.brewerydb.data.styles.datasource.StylesCloudDataSource
import com.txusballesteros.brewerydb.data.styles.datasource.StylesLocalDataSource
import javax.inject.Inject

class GetStyleByIdStrategy private constructor(private val localDataSource: StylesLocalDataSource,
                                               private val cloudDataSource: StylesCloudDataSource):
                           LocalOrCloudStrategy<Int, StyleDataModel>() {

  override fun onRequestCallToLocal(params: Int?): StyleDataModel? {
    return localDataSource.get(params!!)
  }

  override fun onRequestCallToCloud(params: Int?): StyleDataModel? {
    val response = cloudDataSource.getList()
    localDataSource.store(response)
    return localDataSource.get(params!!)
  }

  class Builder @Inject constructor(private val localDataSource: StylesLocalDataSource,
                                    private val cloudDataSource: StylesCloudDataSource) {
    fun build() : GetStyleByIdStrategy {
      return GetStyleByIdStrategy(localDataSource, cloudDataSource)
    }
  }
}