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
package com.txusballesteros.brewerydb.domain.repository

import com.txusballesteros.brewerydb.data.categories.strategy.GetCategoriesStrategy
import com.txusballesteros.brewerydb.data.model.mapToDomain
import com.txusballesteros.brewerydb.domain.model.Category
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoriesRepository @Inject constructor(private val getCategoriesStrategy: GetCategoriesStrategy.Builder) {

  fun get(onResult: (List<Category>) -> Unit) {
    getCategoriesStrategy.build().execute(onResult = {
      val categories = it.orEmpty().map { category -> mapToDomain(category) }
      onResult(categories)
    })
  }
}