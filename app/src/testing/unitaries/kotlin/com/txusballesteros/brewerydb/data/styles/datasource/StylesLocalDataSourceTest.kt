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
package com.txusballesteros.brewerydb.data.styles.datasource

import com.txusballesteros.brewerydb.UnitTest
import com.txusballesteros.brewerydb.data.model.StyleDataModel
import org.junit.Assert
import org.junit.Test
import java.util.*

class StylesLocalDataSourceTest : UnitTest() {
  companion object {
    private val CATEGORY_A: Int = 1
    private val CATEGORY_B: Int = 2
    private val STYLE_ID : Int = 1
    private val STYLE_NAME : String = "Classic English-Style Pale Ale"
    private val STYLE_SHORT_NAME : String = "English Pale"
    private val STYLE_DESCRIPTION : String = "Classic English pale ales are golden to copper colored and display earthy,..."
  }

  lateinit var dataSource: StylesLocalDataSource
  lateinit var stylesList: ArrayList<StyleDataModel>

  override fun onPrepareTest() {
    dataSource = StylesInMemoryLocalDataSource()
    stylesList = ArrayList<StyleDataModel>()
    stylesList.add(StyleDataModel(STYLE_ID, CATEGORY_B, STYLE_NAME, STYLE_SHORT_NAME, STYLE_DESCRIPTION))
    stylesList.add(StyleDataModel(STYLE_ID, CATEGORY_A, STYLE_NAME, STYLE_SHORT_NAME, STYLE_DESCRIPTION))
  }

  @Test
  fun shouldGetStyles() {
    dataSource.store(stylesList)

    val result = dataSource.getStylesByCategoryId(CATEGORY_A)

    Assert.assertFalse(result.isEmpty())
    Assert.assertEquals(CATEGORY_A, result.first().categoryId)
  }

  @Test
  fun shouldBeStore() {
    dataSource.store(stylesList)
    val result = dataSource.getStyles()
    Assert.assertEquals(stylesList.size, result.size)
  }
}