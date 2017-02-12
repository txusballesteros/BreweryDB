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
package com.txusballesteros.brewerydb.threading

import java.util.concurrent.*

class ThreadExecutorPoolFactory {
  companion object {
    private val INITIAL_POOL_SIZE : Int = 5
    private val MAX_POOL_SIZE : Int = 10
    private val KEEP_ALIVE_TIME : Long = 10
    private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
  }

  private val workQueue : BlockingQueue<Runnable>
  private val threadPoolExecutor : ThreadPoolExecutor
  private val threadFactory : ThreadFactory

  fun get() : ExecutorService {
    return threadPoolExecutor
  }

  init {
    this.workQueue = LinkedBlockingQueue<Runnable>()
    this.threadFactory = JobThreadFactory()
    this.threadPoolExecutor = ThreadPoolExecutor(
        INITIAL_POOL_SIZE,
        MAX_POOL_SIZE,
        KEEP_ALIVE_TIME,
        KEEP_ALIVE_TIME_UNIT,
        workQueue,
        threadFactory)
  }

  private class JobThreadFactory : ThreadFactory {
    private var counter = 0

    override fun newThread(runnable: Runnable): Thread {
      return Thread(runnable, THREAD_NAME + counter++)
    }

    companion object {
      private val THREAD_NAME = "android_"
    }
  }
}