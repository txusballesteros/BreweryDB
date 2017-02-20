package com.txusballesteros.brewerydb.api.model

import com.txusballesteros.brewerydb.data.model.StyleDataModel
import javax.inject.Inject

open class StyleApiModelMapper @Inject constructor() {
  fun map(source: List<StyleApiResponse.StyleApiModel>)
      = source.map { style -> map(style) }

  fun map(source: StyleApiResponse.StyleApiModel)
        = StyleDataModel(source.id, source.categoryId,
          source.name, source.shortName, source.description ?: "",
          source.ibuMin, source.ibuMax, source.abvMin,
          source.abvMax, source.srmMin, source.srmMax,
          source.ogMin, source.ogMax, source.fgMin, source.fgMax)
}