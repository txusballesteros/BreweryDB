package com.txusballesteros.brewerydb

import org.junit.Before

abstract class UnitTest {
  @Before
  fun onBefore() {
    onPrepareTest()
  }

  abstract fun onPrepareTest()
}