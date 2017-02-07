package com.txusballesteros.brewerydb.di

import com.txusballesteros.brewerydb.api.di.ApiModuleForTest
import com.txusballesteros.brewerydb.api.di.RestModuleForTest
import com.txusballesteros.brewerydb.api.di.RetrofitModuleForTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModuleForTest::class,
                             RetrofitModuleForTest::class,
                             RestModuleForTest::class))
interface ApiTestComponent {
}