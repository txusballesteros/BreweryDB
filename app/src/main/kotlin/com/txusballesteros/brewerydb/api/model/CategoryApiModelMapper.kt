package com.txusballesteros.brewerydb.api.model

import com.txusballesteros.brewerydb.data.model.CategoryDataModel
import javax.inject.Inject

open class CategoryApiModelMapper @Inject constructor() {
  fun map(source: List<CategoryApiResponse.CategoryApiModel>)
      = source.map { style -> map(style) }

  fun map(source: CategoryApiResponse.CategoryApiModel)
        = CategoryDataModel(source.id, source.name)
}