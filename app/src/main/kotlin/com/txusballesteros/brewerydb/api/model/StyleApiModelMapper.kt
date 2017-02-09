package com.txusballesteros.brewerydb.api.model

import com.txusballesteros.brewerydb.data.model.StyleDataModel
import javax.inject.Inject

open class StyleApiModelMapper @Inject constructor() {
  fun map(source: List<StyleApiResponse.StyleApiModel>)
      = source.map { style -> map(style) }

  fun map(source: StyleApiResponse.StyleApiModel)
        = StyleDataModel(source.id, source.categoryId, source.name, source.shortName, source.description ?: "")
}