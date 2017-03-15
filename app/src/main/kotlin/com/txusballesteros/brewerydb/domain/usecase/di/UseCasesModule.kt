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
package com.txusballesteros.brewerydb.domain.usecase.di

import com.txusballesteros.brewerydb.domain.usecase.beers.*
import com.txusballesteros.brewerydb.domain.usecase.categories.GetCategoriesInteractor
import com.txusballesteros.brewerydb.domain.usecase.categories.GetCategoriesUseCase
import com.txusballesteros.brewerydb.domain.usecase.glassware.GetGlassByIdInteractor
import com.txusballesteros.brewerydb.domain.usecase.glassware.GetGlassByIdUseCase
import com.txusballesteros.brewerydb.domain.usecase.ingredients.GetIngredientInteractor
import com.txusballesteros.brewerydb.domain.usecase.ingredients.GetIngredientUseCase
import com.txusballesteros.brewerydb.domain.usecase.styles.GetStyleByIdInteractor
import com.txusballesteros.brewerydb.domain.usecase.styles.GetStyleByIdUseCase
import com.txusballesteros.brewerydb.domain.usecase.styles.GetStylesInteractor
import com.txusballesteros.brewerydb.domain.usecase.styles.GetStylesUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
  @Provides
  fun provideGetCategoriesUseCase(useCase: GetCategoriesInteractor): GetCategoriesUseCase = useCase

  @Provides
  fun provideGetStylesUseCase(useCase: GetStylesInteractor): GetStylesUseCase = useCase

  @Provides
  fun provideGetBeerByIdUseCase(useCase: GetBeerByIdInteractor): GetBeerByIdUseCase = useCase

  @Provides
  fun provideGetBeersUseCase(useCase: GetBeersInteractor): GetBeersUseCase = useCase

  @Provides
  fun provideStoreBeersQueryUseCase(useCase: StoreBeersQueryInteractor): StoreBeersQueryUseCase = useCase

  @Provides
  fun provideGetBeersQueryUseCase(useCase: GetBeersQueryInteractor): GetBeersQueryUseCase = useCase

  @Provides
  fun provideGetNextPageBeersUseCase(useCase: GetNextPageBeersInteractor): GetNextPageBeersUseCase = useCase

  @Provides
  fun provideGetStyleById(useCase: GetStyleByIdInteractor): GetStyleByIdUseCase = useCase

  @Provides
  fun provideGetGlassByIdUseCase(useCase: GetGlassByIdInteractor): GetGlassByIdUseCase = useCase

  @Provides
  fun provideGetBeerIngredientsUseCase(useCase: GetBeerIngredientsInteractor): GetBeerIngredientsUseCase = useCase

  @Provides
  fun provideGetIngredientUseCase(useCase: GetIngredientInteractor): GetIngredientUseCase = useCase

  @Provides
  fun provideGetBeerBreweriesUseCase(useCase: GetBeerBreweriesInteractor): GetBeerBreweriesUseCase = useCase
}