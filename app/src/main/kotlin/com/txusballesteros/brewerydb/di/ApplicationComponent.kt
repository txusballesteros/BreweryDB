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
package com.txusballesteros.brewerydb.di

import com.txusballesteros.brewerydb.Application
import com.txusballesteros.brewerydb.api.di.ApiModule
import com.txusballesteros.brewerydb.api.di.RestModule
import com.txusballesteros.brewerydb.api.di.RetrofitModule
import com.txusballesteros.brewerydb.api.styles.StylesApi
import com.txusballesteros.brewerydb.data.di.DataSourceModule
import com.txusballesteros.brewerydb.domain.repository.di.RepositoriesModule
import com.txusballesteros.brewerydb.domain.repository.di.RepositoriesProvider
import com.txusballesteros.brewerydb.threading.di.ThreadingProvider
import com.txusballesteros.brewerydb.threading.di.ThreadingModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ThreadingModule::class,
        RepositoriesModule::class,
        DataSourceModule::class,
        ApiModule::class,
        RetrofitModule::class,
        RestModule::class
))
interface ApplicationComponent : RepositoriesProvider, ThreadingProvider {
  fun inject(application: Application)
}