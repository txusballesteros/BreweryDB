package com.txusballesteros.brewerydb

import com.txusballesteros.brewerydb.di.ApiTestComponent
import org.junit.Before

abstract class ApiIntegrationTest {
  @Before
  fun onPrepareTest() {

  }

  private fun initializeInjection() {

  }

  abstract fun onRequestInjection(component: ApiTestComponent)
}