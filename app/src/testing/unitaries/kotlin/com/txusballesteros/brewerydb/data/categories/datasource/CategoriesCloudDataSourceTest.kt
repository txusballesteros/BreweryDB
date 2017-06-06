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

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.txusballesteros.brewerydb.UnitTest
import com.txusballesteros.brewerydb.api.categories.CategoriesApi
import com.txusballesteros.brewerydb.api.model.CategoryApiResponse
import org.junit.Assert
import org.junit.Test
import java.util.*

class CategoriesCloudDataSourceTest: UnitTest() {
  lateinit var dataSource: CategoriesCloudDataSource
  lateinit var api: CategoriesApi

  override fun onPrepareTest() {
    api = mock()
    dataSource = CategoriesRestCloudDataSource(api)
  }

  @Test
  fun shouldGetSortedCategoriesList() {
    val categories = ArrayList<CategoryApiResponse.CategoryApiModel>()
    categories.add(CategoryApiResponse.CategoryApiModel(1, "B"))
    categories.add(CategoryApiResponse.CategoryApiModel(2, "A"))
    val apiResponse = CategoryApiResponse(categories, "Request Successful", "success")
    whenever(api.getCategories()).thenReturn(apiResponse)

    val result = dataSource.getList()

    Assert.assertFalse(result.isEmpty())
    Assert.assertEquals(2, result.first().id)
    Assert.assertEquals("A", result.first().name)
  }
}