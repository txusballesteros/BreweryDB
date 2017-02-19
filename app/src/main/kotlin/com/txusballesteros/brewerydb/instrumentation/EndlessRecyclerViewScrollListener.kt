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
package com.txusballesteros.brewerydb.instrumentation

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

abstract class EndlessRecyclerViewScrollListener constructor(private val layoutManager: RecyclerView.LayoutManager):
                                                      RecyclerView.OnScrollListener() {
  private var visibleThreshold = 5
  private var currentPage = 0
  private var previousTotalItemCount = 0
  private var loading = true
  private val startingPageIndex = 0

  init {
    if (layoutManager is StaggeredGridLayoutManager) {
      visibleThreshold *= layoutManager.spanCount
    } else if (layoutManager is GridLayoutManager) {
      visibleThreshold *= layoutManager.spanCount
    }
  }

  fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
    var maxSize = 0
    for (i in lastVisibleItemPositions.indices) {
      if (i == 0) {
        maxSize = lastVisibleItemPositions[i]
      } else if (lastVisibleItemPositions[i] > maxSize) {
        maxSize = lastVisibleItemPositions[i]
      }
    }
    return maxSize
  }

  override fun onScrolled(view: RecyclerView?, dx: Int, dy: Int) {
    var lastVisibleItemPosition = 0
    val totalItemCount = layoutManager.itemCount

    if (layoutManager is StaggeredGridLayoutManager) {
      val lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null)
      lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
    } else if (layoutManager is GridLayoutManager) {
      lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
    } else if (layoutManager is LinearLayoutManager) {
      lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
    }

    if (loading && totalItemCount > previousTotalItemCount) {
      loading = false
      previousTotalItemCount = totalItemCount
    }

    if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
      currentPage++
      onLoadMore(currentPage, totalItemCount, view)
      loading = true
    }
  }

  fun resetState() {
    this.currentPage = this.startingPageIndex
    this.previousTotalItemCount = 0
    this.loading = true
  }

  abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?)
}