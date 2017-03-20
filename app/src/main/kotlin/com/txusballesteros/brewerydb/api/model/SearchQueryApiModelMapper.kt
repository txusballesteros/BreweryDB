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
package com.txusballesteros.brewerydb.api.model

import com.txusballesteros.brewerydb.data.model.SearchQueryDataModel
import javax.inject.Inject

class SearchQueryApiModelMapper @Inject constructor() {
  fun map(source: SearchQueryDataModel)
      = SearchQueryApiModel(mapKeyword(source.keyword),
                            mapRange(source.abvMin, source.abvMax),
                            mapRange(source.ibuMin, source.ibuMax),
                            mapBoolean(source.isOrganic),
                            source.breweryId,
                            source.styleId)

  private fun mapRange(sourceMin: Int?, sourceMax: Int?): String? {
    var result: String? = null
    if (sourceMin != null && sourceMax != null) {
      result = "$sourceMin,$sourceMax"
    } else if (sourceMin != null && sourceMax == null) {
      result = "+$sourceMin"
    } else if (sourceMin == null && sourceMax != null) {
      result = "-$sourceMax"
    }
    return result
  }

  private fun mapBoolean(source: Boolean?): String? {
    var result: String? = null
    if (source != null) {
      if (source) {
        result = "Y"
      } else {
        result = "N"
      }
    }
    return result
  }

  private fun mapKeyword(source: String?): String? {
    var result: String? = null
    if (source != null) {
      result = "$source*"
    }
    return result
  }
}