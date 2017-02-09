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
package com.txusballesteros.brewerydb.domain.usecase

import com.txusballesteros.brewerydb.exception.ApplicationException
import com.txusballesteros.brewerydb.threading.PostExecutionThread
import com.txusballesteros.brewerydb.threading.ThreadExecutor

abstract class UseCase<T> (protected val executor: ThreadExecutor, protected val postExecutorThread: PostExecutionThread) {
  private lateinit var callback: UseCaseCallback<T>

  open protected fun execute(callback: UseCaseCallback<T>) {
    this.callback = callback
    runExecution()
  }

  private fun runExecution() {
    executor.execute {
      try {
        onExecute()
      } catch (error: ApplicationException) {
        notifyOnError(error)
      }
    }
  }

  protected abstract fun onExecute()

  fun notifyOnResult(result: T) {
    postExecutorThread.execute(Runnable {
      callback.onResult(result)
    })
  }

  fun notifyOnError(error: ApplicationException) {
    postExecutorThread.execute(Runnable {
      callback.onError(error)
    })
  }

  interface UseCaseCallback<T> {
    fun onResult(result: T)
    fun onError(error: ApplicationException)
  }
}