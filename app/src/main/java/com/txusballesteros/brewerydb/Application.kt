package com.txusballesteros.brewerydb

import android.app.Application
import com.txusballesteros.brewerydb.di.ApplicationComponent
import com.txusballesteros.brewerydb.di.DaggerApplicationComponent

class Application : Application() {
  lateinit var applicationComponent : ApplicationComponent

  override fun onCreate() {
    super.onCreate()
    initializeDependencyInjections()
  }

  fun initializeDependencyInjections() {
    this.applicationComponent = DaggerApplicationComponent.builder()
                                        .build()
  }
}