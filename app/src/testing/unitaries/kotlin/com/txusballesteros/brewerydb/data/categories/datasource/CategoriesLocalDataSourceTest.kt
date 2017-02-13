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
package com.txusballesteros.brewerydb.data.categories.datasource

import com.txusballesteros.brewerydb.UnitTest
import com.txusballesteros.brewerydb.data.model.CategoryDataModel
import org.junit.Assert
import org.junit.Test
import java.util.*

class CategoriesLocalDataSourceTest: UnitTest() {
  lateinit var dataSource: CategoriesLocalDataSource
  lateinit var categoriesList: ArrayList<CategoryDataModel>

  override fun onPrepareTest() {
    dataSource = CategoriesInMemoryLocalDataSource()
    categoriesList = ArrayList()
    categoriesList.add(CategoryDataModel(1, "A"))
    categoriesList.add(CategoryDataModel(2, "B"))
  }

  @Test
  fun shouldGetCategories() {
    dataSource.store(categoriesList)

    val result = dataSource.getCategories()

    Assert.assertFalse(result.isEmpty())
    Assert.assertEquals(2, result.size)
    Assert.assertEquals(1, result.first().id)
    Assert.assertEquals("A", result.first().name)
  }

  @Test
  fun shouldStoreCategories() {
    dataSource.store(categoriesList)

    val result = dataSource.getCategories()
    Assert.assertEquals(categoriesList.size, result.size)
  }
}