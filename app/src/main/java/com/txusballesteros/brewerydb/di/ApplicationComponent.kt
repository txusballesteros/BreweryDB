package com.txusballesteros.brewerydb.di

import com.txusballesteros.brewerydb.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
  fun inject(application: Application)
}