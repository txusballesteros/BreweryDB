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
package com.txusballesteros.brewerydb.api.di

import com.txusballesteros.brewerydb.api.beers.BeersApi
import com.txusballesteros.brewerydb.api.beers.BeersRetrofitApi
import com.txusballesteros.brewerydb.api.categories.CategoriesApi
import com.txusballesteros.brewerydb.api.categories.CategoriesRetrofitApi
import com.txusballesteros.brewerydb.api.glassware.GlasswareApi
import com.txusballesteros.brewerydb.api.glassware.GlasswareRetrofitApi
import com.txusballesteros.brewerydb.api.beers.BeerIngredientsApi
import com.txusballesteros.brewerydb.api.beers.BeerIngredientsRetrofitApi
import com.txusballesteros.brewerydb.api.styles.StylesApi
import com.txusballesteros.brewerydb.api.styles.StylesRetrofitApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {
  @Singleton @Provides
  fun provideCategoriesApi(api: CategoriesRetrofitApi): CategoriesApi = api

  @Singleton @Provides
  fun provideStylesApi(api: StylesRetrofitApi) : StylesApi = api

  @Singleton @Provides
  fun provideBeersApi(api: BeersRetrofitApi): BeersApi = api

  @Singleton @Provides
  fun provideGlasswareApi(api: GlasswareRetrofitApi): GlasswareApi = api

  @Singleton @Provides
  fun provideIngredientsApi(api: BeerIngredientsRetrofitApi): BeerIngredientsApi = api
}