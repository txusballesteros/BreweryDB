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
package com.txusballesteros.brewerydb.data.categories.strategy

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.txusballesteros.brewerydb.UnitTest
import com.txusballesteros.brewerydb.data.categories.datasource.CategoriesCloudDataSource
import com.txusballesteros.brewerydb.data.categories.datasource.CategoriesLocalDataSource
import com.txusballesteros.brewerydb.data.model.CategoryDataModel
import org.junit.Assert
import org.junit.Test
import java.util.*

class GetCategoriesStrategyTest: UnitTest() {
  lateinit var strategy: GetCategoriesStrategy
  lateinit var localDataSource: CategoriesLocalDataSource
  lateinit var cloudDataSource: CategoriesCloudDataSource
  lateinit var categoriesList: ArrayList<CategoryDataModel>

  override fun onPrepareTest() {
    localDataSource = mock()
    cloudDataSource = mock()
    categoriesList = ArrayList()
    categoriesList.add(CategoryDataModel(1, "A"))
    strategy = GetCategoriesStrategy.Builder(localDataSource, cloudDataSource).build()
  }

  @Test
  fun shouldGetFromCloud() {
    whenever(localDataSource.getCategories()).thenReturn(null)
    whenever(cloudDataSource.getCategories()).thenReturn(categoriesList)

    strategy.execute(onResult =  {
        Assert.assertNotNull(it)
        Assert.assertEquals(categoriesList.size, it?.size)
        Assert.assertEquals(1, it?.first()?.id)
        verify(cloudDataSource).getCategories()
    })
  }

  @Test
  fun shouldGetFromLocal() {
    whenever(localDataSource.getCategories()).thenReturn(categoriesList)

    strategy.execute(onResult =  {
        Assert.assertNotNull(it)
        Assert.assertEquals(categoriesList.size, it?.size)
        Assert.assertEquals(1, it?.first()?.id)
        verify(cloudDataSource, never()).getCategories()
    })
  }
}