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
package com.txusballesteros.brewerydb.data.model

import com.txusballesteros.brewerydb.domain.model.Style
import javax.inject.Inject

class StyleDataModelMapper @Inject constructor() {
  fun map(source: List<StyleDataModel>?)
       = source?.map { style -> map(style) }

  fun map(source: StyleDataModel)
      = Style(source.id, source.categoryId,
              source.name, source.shortName, source.description,
              source.ibuMin, source.ibuMax, source.abvMin,
              source.abvMax, source.srmMin, source.srmMax,
              source.ogMin, source.ogMax, source.fgMin, source.fgMax)
}