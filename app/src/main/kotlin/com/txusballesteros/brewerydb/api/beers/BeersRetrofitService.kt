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
package com.txusballesteros.brewerydb.api.beers

import com.txusballesteros.brewerydb.api.model.BeerApiResponse
import com.txusballesteros.brewerydb.api.model.BeersListApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BeersRetrofitService {
  @GET("/v2/beers")
  fun getBeers(@Query("styleId") styleId: Int,
               @Query("hasLabels") withLabels: String,
               @Query("status") status: String,
               @Query("p") page: Int): Call<BeersListApiResponse>

  @GET("/v2/beer/{beerId}")
  fun getBeer(@Path("beerId") beerId: String): Call<BeerApiResponse>
}