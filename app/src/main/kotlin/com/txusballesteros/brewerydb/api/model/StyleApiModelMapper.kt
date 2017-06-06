package com.txusballesteros.brewerydb.api.model

import com.txusballesteros.brewerydb.data.model.StyleDataModel

fun mapToData(source: StyleApiResponse.StyleApiModel) = StyleDataModel(source.id,
                                                                 source.categoryId,
                                                                 source.name,
                                                                 source.shortName,
                                                                 source.description ?: "",
                                                                 source.ibuMin ?: "",
                                                                 source.ibuMax ?: "",
                                                                 source.abvMin ?: "",
                                                                 source.abvMax ?: "",
                                                                 source.srmMin ?: "",
                                                                 source.srmMax ?: "",
                                                                 source.ogMin ?: "",
                                                                 source.ogMax ?: "",
                                                                 source.fgMin ?: "",
                                                                 source.fgMax ?: "")