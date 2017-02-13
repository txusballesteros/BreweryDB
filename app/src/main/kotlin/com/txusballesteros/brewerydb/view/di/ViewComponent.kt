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
package com.txusballesteros.brewerydb.view.di

import com.txusballesteros.brewerydb.di.ApplicationComponent
import com.txusballesteros.brewerydb.di.scopes.PerView
import com.txusballesteros.brewerydb.domain.usecase.di.UseCasesModule
import com.txusballesteros.brewerydb.presentation.di.PresentationModule
import com.txusballesteros.brewerydb.view.categories.CategoriesListFragment
import com.txusballesteros.brewerydb.view.styles.StylesListFragment
import dagger.Component

@PerView
@Component(dependencies = arrayOf(
               ApplicationComponent::class),
           modules = arrayOf(
               PresentationModule::class,
               UseCasesModule::class
           ))
interface ViewComponent {
  fun inject(view: CategoriesListFragment)
  fun inject(view: StylesListFragment)
}