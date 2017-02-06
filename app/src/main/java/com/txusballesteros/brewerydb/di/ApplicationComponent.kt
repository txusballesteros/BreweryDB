package com.txusballesteros.brewerydb.di

import com.txusballesteros.brewerydb.Application
import com.txusballesteros.brewerydb.api.di.ApiModule
import com.txusballesteros.brewerydb.api.di.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class,
                             RetrofitModule::class))
interface ApplicationComponent {
  fun inject(application: Application)
}