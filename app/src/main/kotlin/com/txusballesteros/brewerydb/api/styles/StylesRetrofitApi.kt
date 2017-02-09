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
package com.txusballesteros.brewerydb.api.styles

import com.txusballesteros.brewerydb.api.model.StyleApiResponse
import com.txusballesteros.brewerydb.exception.NetworkException
import javax.inject.Inject

class StylesRetrofitApi @Inject constructor(val service: StylesRetrofitService) : StylesApi {
  override fun getStyles() : StyleApiResponse {
    try {
      val call = service.getStyles()
      val response = call.execute()
      return response.body()
    } catch (error: Exception) {
      throw NetworkException(error)
    }
  }
}