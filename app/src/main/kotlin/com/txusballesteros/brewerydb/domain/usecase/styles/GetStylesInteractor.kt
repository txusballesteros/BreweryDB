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
package com.txusballesteros.brewerydb.domain.usecase.styles

import com.txusballesteros.brewerydb.domain.model.Style
import com.txusballesteros.brewerydb.domain.repository.Repository
import com.txusballesteros.brewerydb.domain.repository.StylesRepository
import com.txusballesteros.brewerydb.domain.usecase.UseCase
import com.txusballesteros.brewerydb.threading.PostExecutionThread
import com.txusballesteros.brewerydb.threading.ThreadExecutor
import javax.inject.Inject

class GetStylesInteractor @Inject constructor(executor: ThreadExecutor,
                                              postExecutorThread: PostExecutionThread,
                                              private val stylesRepository: StylesRepository):
                       UseCase<List<Style>>(executor, postExecutorThread), GetStylesUseCase {
  var categoryId: Int = 0

  override fun execute(categoryId: Int, callback: UseCaseCallback<List<Style>>) {
    this.categoryId = categoryId
    super.execute(callback)
  }

  override fun onExecute() {
    stylesRepository.getStylesByCategoryId(categoryId, object : Repository.RepositoryCallback<List<Style>> {
      override fun onResult(result: List<Style>) {
        notifyOnResult(result)
      }
    })
  }
}