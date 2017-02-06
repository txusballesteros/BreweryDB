package com.txusballesteros.brewerydb.di

import com.txusballesteros.brewerydb.Application
import com.txusballesteros.brewerydb.api.di.ApiModule
import com.txusballesteros.brewerydb.api.di.RetrofitModule
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ApiModule::class,
                           RetrofitModule::class))
class ApplicationModule(val application: Application) {
  @Provides
  fun provideApplication() : Application {
    return application
  }
}