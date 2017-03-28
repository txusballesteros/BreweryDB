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
package com.txusballesteros.brewerydb.presentation.di

import com.txusballesteros.brewerydb.presentation.beers.*
import com.txusballesteros.brewerydb.presentation.breweries.BreweryDetailPresenter
import com.txusballesteros.brewerydb.presentation.breweries.BreweryDetailPresenterImpl
import com.txusballesteros.brewerydb.presentation.ingredients.IngredientDetailPresenter
import com.txusballesteros.brewerydb.presentation.ingredients.IngredientDetailPresenterImpl
import com.txusballesteros.brewerydb.presentation.ingredients.IngredientDetailControllerPresenter
import com.txusballesteros.brewerydb.presentation.ingredients.IngredientDetailControllerPresenterImpl
import com.txusballesteros.brewerydb.presentation.search.*
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
  @Provides
  fun provideBeersListPresenter(presenter: BeersListPresenterImpl): BeersListPresenter = presenter

  @Provides
  fun provideBeerDetailPresenter(presenter: BeerDetailPresenterImpl): BeerDetailPresenter = presenter

  @Provides
  fun provideBeerIbuPresenter(presenter: BeerIbuPresenterImpl): BeerIbuPresenter = presenter

  @Provides
  fun provideBeerAbvPresenter(presenter: BeerAbvPresenterImpl): BeerAbvPresenter = presenter

  @Provides
  fun provideBeerDetailControllerPresenter(presenter: BeerDetailControllerPresenterImpl):
      BeerDetailControllerPresenter = presenter

  @Provides
  fun provideBeerIngredientsPresenter(presenter: BeerIngredientsPresenterImpl): BeerIngredientsPresenter = presenter

  @Provides
  fun provideIngredientDetailControllerPresenter(presenter: IngredientDetailControllerPresenterImpl):
      IngredientDetailControllerPresenter = presenter

  @Provides
  fun provideHopDetailPresenter(presenter: IngredientDetailPresenterImpl): IngredientDetailPresenter = presenter

  @Provides
  fun provideBeerBreweriesPresenter(presenter: BeerBreweriesPresenterImpl): BeerBreweriesPresenter = presenter

  @Provides
  fun provideBreweryDetailPresenter(presenter: BreweryDetailPresenterImpl): BreweryDetailPresenter = presenter

  @Provides
  fun provideSearchPresenter(presenter: SearchPresenterImpl): SearchPresenter = presenter

  @Provides
  fun provideStyleListSelectorPresenter(presenter: StyleListSelectorPresenterImpl): StyleListSelectorPresenter = presenter

  @Provides
  fun provideSeachSectionPresenter(presenter: SeachSectionPresenterImpl): SeachSectionPresenter = presenter
}