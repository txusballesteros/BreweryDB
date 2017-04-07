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
package com.txusballesteros.brewerydb.view.search

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.extesion.inflate
import com.txusballesteros.brewerydb.presentation.model.StyleViewModel
import kotlinx.android.synthetic.main.item_style.view.*
import java.util.*

class StyleListAdapter(private val onStyleSelected: (StyleViewModel) -> Unit): RecyclerView.Adapter<StyleListAdapter.ViewHolder>() {
  private val cache: MutableList<StyleViewModel> = ArrayList()

  fun clear() {
    cache.clear()
  }

  fun addAll(styles: List<StyleViewModel>) {
    cache.addAll(styles)
  }

  override fun getItemCount(): Int = cache.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    = ViewHolder(parent.inflate(R.layout.item_style))

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.itemView) {
    val style = cache[position]
    text.text = style.name
    setOnClickListener { onStyleSelected(style) }
  }

  class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}