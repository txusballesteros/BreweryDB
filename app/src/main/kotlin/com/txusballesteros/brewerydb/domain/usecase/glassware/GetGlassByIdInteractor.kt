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
package com.txusballesteros.brewerydb.domain.usecase.glassware

import com.txusballesteros.brewerydb.domain.model.Glass
import com.txusballesteros.brewerydb.domain.repository.GlasswareRepository
import com.txusballesteros.brewerydb.domain.usecase.AnkoUseCase
import com.txusballesteros.brewerydb.exception.ApplicationException
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class GetGlassByIdInteractor @Inject constructor(executor: ExecutorService,
                                                 private val repository: GlasswareRepository):
                             AnkoUseCase<Glass>(executor), GetGlassByIdUseCase {
  private var glassId = 0

  override fun execute(id: Int, onResult: (Glass) -> Unit, onError: (ApplicationException) -> Unit) {
    glassId = id
    super.execute(onResult, onError)
  }

  override fun onExecute(onResult: (Glass) -> Unit) {
    repository.getGlassById(glassId, {
      onResult(it)
    })
  }
}