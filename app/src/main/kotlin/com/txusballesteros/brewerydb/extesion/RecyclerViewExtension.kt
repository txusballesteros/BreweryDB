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
package com.txusballesteros.brewerydb.extesion

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE

fun RecyclerView.setEmptyView(view: View) {
  adapter?.apply {
    val observer = object: RecyclerView.AdapterDataObserver() {
      override fun onChanged() {
        val itemCount = adapter.itemCount
        view.visibility = if (itemCount == 0) VISIBLE else GONE
        this@setEmptyView.visibility = if (itemCount == 0) GONE else VISIBLE
      }
    }
    adapter.registerAdapterDataObserver(observer)
    observer.onChanged()
  }
}
