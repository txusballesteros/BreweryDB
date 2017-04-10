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
import com.txusballesteros.brewerydb.api.model.StyleApiModelMapper
import com.txusballesteros.brewerydb.api.model.StyleApiResponse
import com.txusballesteros.brewerydb.api.styles.StylesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import java.util.*

class StylesCloudDataSourceTest : UnitTest() {
  companion object {
    private val CATEGORY_ID : Int = 1
    private val STYLE_ID : Int = 1
    private val STYLE_NAME : String = "Classic English-Style Pale Ale"
    private val STYLE_SHORT_NAME : String = "English Pale"
    private val STYLE_DESCRIPTION : String = "Classic English pale ales are golden to copper colored and display earthy,..."
  }

  lateinit var dataSource: StylesCloudDataSource
  lateinit var stylesApiMock: StylesApi
  lateinit var stylesApiDataMapperMock: StyleApiModelMapper

  override fun onPrepareTest() {
    stylesApiMock = Mockito.mock(StylesApi::class.java)
    stylesApiDataMapperMock = Mockito.mock(StyleApiModelMapper::class.java)
    dataSource = StylesRestCloudDataSource(stylesApiMock, stylesApiDataMapperMock)
  }

  @Test
  fun shouldGetStyles() {
    val style = StyleApiResponse.StyleApiModel(STYLE_ID, CATEGORY_ID, STYLE_NAME, STYLE_SHORT_NAME, STYLE_DESCRIPTION)
    val styles = ArrayList<StyleApiResponse.StyleApiModel>()
    styles.add(style)
    val apiResponse = StyleApiResponse(styles, "Request Successful", "success")
    doReturn(apiResponse).`when`(stylesApiMock).getStyles()

    val result = dataSource.getList()

    assertFalse(result.isEmpty())
    assertEquals(CATEGORY_ID, result.first().categoryId)
    assertEquals(STYLE_ID, result.first().id)
    assertEquals(STYLE_NAME, result.first().name)
    assertEquals(STYLE_SHORT_NAME, result.first().shortName)
    assertEquals(STYLE_DESCRIPTION, result.first().description)
  }
}