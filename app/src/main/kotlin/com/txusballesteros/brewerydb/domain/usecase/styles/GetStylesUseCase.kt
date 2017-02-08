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

class GetStylesUseCase @Inject constructor(executor: ThreadExecutor,
                                           postExecutorThread: PostExecutionThread,
                                           private val repository: StylesRepository) : UseCase(executor, postExecutorThread) {
  private lateinit var postCallback: UseCaseCallback<List<Style>>

  fun execute(postCallback: UseCaseCallback<List<Style>>) {
    try {
      this.postCallback = postCallback
      executor.execute ({
        repository.getStyles(callback = object : Repository.RepositoryCallback<List<Style>> {
          override fun onResult(result: List<Style>) {
            notifyOnResult(result)
          }
        })
      })
    } catch (error: Exception) {
      notifyOnError()
    }
  }

  private fun notifyOnResult(result: List<Style>) {
    postExecutorThread.execute(Runnable {
      postCallback.onResult(result)
    })
  }

  private fun notifyOnError() {
    postExecutorThread.execute(Runnable {
      postCallback.onError()
    })
  }
}