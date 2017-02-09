package com.txusballesteros.brewerydb.data.styles.datasource

import com.txusballesteros.brewerydb.data.model.StyleDataModel
import javax.inject.Inject

class StylesInMemoryLocalDataSource @Inject constructor() : StylesLocalDataSource {
  private var cache: List<StyleDataModel>? = null

  override fun get(): List<StyleDataModel>? {
    return cache
  }

  override fun store(styles: List<StyleDataModel>) {
    this.cache = styles
  }
}