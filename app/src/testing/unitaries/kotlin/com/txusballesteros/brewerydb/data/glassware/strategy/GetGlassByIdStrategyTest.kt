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
package com.txusballesteros.brewerydb.data.glassware.strategy

import com.nhaarman.mockito_kotlin.*
import com.txusballesteros.brewerydb.UnitTest
import com.txusballesteros.brewerydb.data.glassware.datasource.GlasswareCloudDataSource
import com.txusballesteros.brewerydb.data.glassware.datasource.GlasswareLocalDataSource
import com.txusballesteros.brewerydb.data.model.GlassDataModel
import org.junit.Assert.assertNotNull
import org.junit.Test

class GetGlassByIdStrategyTest: UnitTest() {
  companion object {
    val GLASS_ID = 1
    val GLASS_NAME = "Pilsner"
  }

  lateinit var localDataSource: GlasswareLocalDataSource
  lateinit var cloudDataSource: GlasswareCloudDataSource
  lateinit var strategy: GetGlassByIdStrategy

  override fun onPrepareTest() {
    localDataSource = mock()
    cloudDataSource = mock()
    strategy = GetGlassByIdStrategy.Builder(localDataSource, cloudDataSource).build()
  }

  @Test
  fun shouldGetFromLocal() {
    val glass = GlassDataModel(GLASS_ID, GLASS_NAME)
    whenever(localDataSource.getGlassById(any())).thenReturn(glass)

    strategy.execute(GLASS_ID, onResult = {
      assertNotNull(it)
      verify(cloudDataSource, never()).getGlassware()
    })
  }

  @Test
  fun shouldGetFromCloud() {
    whenever(localDataSource.getGlassById(any())).thenReturn(null)

    strategy.execute(GLASS_ID, onResult = {
      verify(cloudDataSource).getGlassware()
    })
  }
}