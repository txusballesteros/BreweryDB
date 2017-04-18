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
package com.txusballesteros.brewerydb

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.txusballesteros.brewerydb.di.ApplicationComponent
import com.txusballesteros.brewerydb.di.ApplicationModule
import com.txusballesteros.brewerydb.di.DaggerApplicationComponent

class Application : Application() {
  lateinit var applicationComponent : ApplicationComponent
              private set

  override fun onCreate() {
    super.onCreate()
    initializeDependencyInjections()
    initializeStetho()
    initializeLeakCanary()
  }

  private fun initializeLeakCanary() {
    if (!LeakCanary.isInAnalyzerProcess(this)) {
      LeakCanary.install(this)
    }
  }

  fun initializeDependencyInjections() {
    this.applicationComponent = DaggerApplicationComponent.builder()
                                        .applicationModule(ApplicationModule(this))
                                        .build()
  }

  fun initializeStetho() {
    Stetho.initializeWithDefaults(this)
  }
}