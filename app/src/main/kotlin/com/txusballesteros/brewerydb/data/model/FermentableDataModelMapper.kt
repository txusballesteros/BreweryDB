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
import javax.inject.Inject

class FermentableDataModelMapper @Inject constructor(private val countryMapper: CountryDataModelMapper) {
  fun map(source: FermentableDataModel): Fermentable
    = Fermentable(source.id,
                  source.name,
                  source.description,
                  countryMapper.map(source.country),
                  source.countryOfOrigin,
                  source.srmId,
                  source.srmPrecise,
                  source.moistureContent,
                  source.dryYield,
                  source.potential,
                  source.protein,
                  source.maxInBatch,
                  source.requiresMashing)
}