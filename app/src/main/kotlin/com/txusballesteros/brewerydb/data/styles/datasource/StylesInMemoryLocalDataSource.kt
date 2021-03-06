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

import com.txusballesteros.brewerydb.data.model.StyleDataModel
import kotlin.collections.ArrayList
import javax.inject.Inject

class StylesInMemoryLocalDataSource @Inject constructor(): StylesLocalDataSource {
  private val cache: MutableMap<Int, StyleDataModel> = HashMap()

  override fun get(styleId: Int): StyleDataModel? {
    return cache[styleId]
  }

  override fun getList(): List<StyleDataModel> {
    val sortedList = cache.values.sortedBy { it.name }
    return ArrayList<StyleDataModel>(sortedList)
  }

  override fun store(styles: List<StyleDataModel>) {
    styles.map { cache.put(it.id, it) }
  }
}