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
package com.txusballesteros.brewerydb.data.strategy

abstract class Strategy<in INPUT, OUTPUT> {
  lateinit var onResult: (OUTPUT?) -> Unit
  lateinit var onError: (Exception?) -> Unit

  fun execute(parameter: INPUT? = null,
              onResult: (OUTPUT?) -> Unit = { },
              onError: (Exception?) -> Unit = { }) {
    this.onResult = onResult
    this.onError = onError
    run(parameter)
  }

  protected abstract fun run(params: INPUT?)

  fun notifyOnResult(result: OUTPUT? = null) {
    onResult(result)
  }

  fun notifyOnError(error: Exception? = null) {
    onError(error)
  }
}