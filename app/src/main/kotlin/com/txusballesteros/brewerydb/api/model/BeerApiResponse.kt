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

import com.google.gson.annotations.SerializedName

class BeerApiResponse(@SerializedName("data") val beers: List<BeerApiModel>,
                      val message: String,
                      val status: String,
                      val currentPage: Int,
                      val numberOfPages: Int,
                      val totalResults: Int) {

  data class BeerApiModel(val id: String,
                          val name: String?,
                          @SerializedName("nameDisplay") val displayName: String?,
                          val description: String?,
                          val styleId: Int?,
                          val abv: String?,
                          val glasswareId: Int?,
                          val isOrganic: String?,
                          val status: String?,
                          val labels: LabelApiModel?,
                          val servingTemperature: String?)

  data class LabelApiModel(val icon: String?, val medium: String?, val large: String?)
}