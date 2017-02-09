package com.txusballesteros.brewerydb.data.styles.datasource

import com.txusballesteros.brewerydb.data.model.StyleDataModel
import java.util.*
import javax.inject.Inject

class StylesInMemoryLocalDataSource @Inject constructor() : StylesLocalDataSource {
  override fun getByCategoryId(categoryId: Int): List<StyleDataModel> {
    val result: MutableList<StyleDataModel> = ArrayList()
    cache?.filterTo(result) { it.categoryId == categoryId }
    return result
  }

  private var cache: List<StyleDataModel>? = null

  override fun get(): List<StyleDataModel>? {
    return cache
  }

  override fun store(styles: List<StyleDataModel>) {
    this.cache = styles
  }
}