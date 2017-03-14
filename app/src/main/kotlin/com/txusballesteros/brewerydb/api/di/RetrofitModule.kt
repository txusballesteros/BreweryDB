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

import com.txusballesteros.brewerydb.api.beers.BeerBreweriesRetrofitService
import com.txusballesteros.brewerydb.api.beers.BeerIngredientsRetrofitService
import com.txusballesteros.brewerydb.api.beers.BeersRetrofitService
import com.txusballesteros.brewerydb.api.categories.CategoriesRetrofitService
import com.txusballesteros.brewerydb.api.glassware.GlasswareRetrofitService
import com.txusballesteros.brewerydb.api.ingredients.FermentableRetrofitService
import com.txusballesteros.brewerydb.api.ingredients.HopsRetrofitService
import com.txusballesteros.brewerydb.api.ingredients.YeastsRetrofitService
import com.txusballesteros.brewerydb.api.styles.StylesRetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RetrofitModule {
  @Provides
  fun provideCategoriesRetrofitService(retrofit: Retrofit): CategoriesRetrofitService
      = retrofit.create(CategoriesRetrofitService::class.java)

  @Provides
  fun provideStylesRetrofitService(retrofit: Retrofit): StylesRetrofitService
      = retrofit.create(StylesRetrofitService::class.java)

  @Provides
  fun provideBeersRetrofitService(retrofit: Retrofit): BeersRetrofitService
      = retrofit.create(BeersRetrofitService::class.java)

  @Provides
  fun provideGlasswareRetrofitService(retrofit: Retrofit): GlasswareRetrofitService
      = retrofit.create(GlasswareRetrofitService::class.java)

  @Provides
  fun provideIngredientsRetrofitService(retrofit: Retrofit): BeerIngredientsRetrofitService
      = retrofit.create(BeerIngredientsRetrofitService::class.java)

  @Provides
  fun provideHopsRetrofitService(retrofit: Retrofit): HopsRetrofitService
      = retrofit.create(HopsRetrofitService::class.java)

  @Provides
  fun provideYeastRetrofitService(retrofit: Retrofit): YeastsRetrofitService
      = retrofit.create(YeastsRetrofitService::class.java)

  @Provides
  fun provideFermentableRetrofitService(retrofit: Retrofit): FermentableRetrofitService
      = retrofit.create(FermentableRetrofitService::class.java)

  @Provides
  fun provideBeerBreweriesRetrofitService(retrofit: Retrofit): BeerBreweriesRetrofitService
      = retrofit.create(BeerBreweriesRetrofitService::class.java)
}