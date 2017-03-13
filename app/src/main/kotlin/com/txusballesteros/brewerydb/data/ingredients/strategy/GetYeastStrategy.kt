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
package com.txusballesteros.brewerydb.data.ingredients.strategy

import com.txusballesteros.brewerydb.data.ingredients.datasource.YeastsCloudDataSource
import com.txusballesteros.brewerydb.data.ingredients.datasource.YeastsLocalDataSource
import com.txusballesteros.brewerydb.data.model.YeastDataModel
import com.txusballesteros.brewerydb.data.strategy.LocalOrCloudStrategy
import javax.inject.Inject

class GetYeastStrategy private constructor(private val localDataSource: YeastsLocalDataSource,
                                           private val cloudDataSource: YeastsCloudDataSource):
                     LocalOrCloudStrategy<Int, YeastDataModel>() {

  override fun onRequestCallToLocal(params: Int?): YeastDataModel? {
    return localDataSource.get(params!!)
  }

  override fun onRequestCallToCloud(params: Int?): YeastDataModel? {
    val response = cloudDataSource.get(params!!)
    localDataSource.store(response)
    return response
  }

  class Builder @Inject constructor(private val localDataSource: YeastsLocalDataSource,
                                    private val cloudDataSource: YeastsCloudDataSource) {
    fun build(): GetYeastStrategy
      =  GetYeastStrategy(localDataSource, cloudDataSource)
  }
}