/*
 * Copyright Txus Ballesteros 2017 (@txusballesteros)
 *
 * This file is part of Foobar.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.txusballesteros.brewerydb.data.model

import com.txusballesteros.brewerydb.domain.model.Fermentable
import com.txusballesteros.brewerydb.domain.model.Hop
import com.txusballesteros.brewerydb.domain.model.Yeast

fun mapToDomain(source: YeastDataModel) = Yeast(source.id,
                                                source.name,
                                                source.description,
                                                source.yeastType,
                                                source.attenuationMin,
                                                source.attenuationMax,
                                                source.fermentTempMin,
                                                source.fermentTempMax,
                                                source.alcoholToleranceMin,
                                                source.alcoholToleranceMax,
                                                source.productId,
                                                source.supplier,
                                                source.yeastFormat)

fun mapToDomain(source: HopDataModel) = Hop(source.id,
                                            source.name,
                                            source.description,
                                            source.alphaAcidMin,
                                            source.alphaAcidMax,
                                            source.betaAcidMin,
                                            source.betaAcidMax,
                                            source.humuleneMin,
                                            source.humuleneMax,
                                            source.caryophylleneMin,
                                            source.caryophylleneMax,
                                            source.cohumuloneMin,
                                            source.cohumuloneMax,
                                            source.myrceneMin,
                                            source.myrceneMax,
                                            source.farneseneMin,
                                            source.farneseneMax,
                                            source.countryOfOrigin,
                                            mapToDomain(source.country))

fun mapToDomain(source: FermentableDataModel) = Fermentable(source.id,
                                                            source.name,
                                                            source.description,
                                                            mapToDomain(source.country),
                                                            source.countryOfOrigin,
                                                            source.srmId,
                                                            source.srmPrecise,
                                                            source.moistureContent,
                                                            source.coarseFineDifference,
                                                            source.diastaticPower,
                                                            source.dryYield,
                                                            source.potential,
                                                            source.protein,
                                                            source.solubleNitrogenRatio,
                                                            source.maxInBatch,
                                                            source.requiresMashing)