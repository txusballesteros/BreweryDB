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
package com.txusballesteros.brewerydb.view.styles

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.model.StyleViewModel
import java.util.*

class StyleListAdapter(private val listener: OnStyleClickListener) : RecyclerView.Adapter<StyleListAdapter.ViewHolder>() {
  private val cache: MutableList<StyleViewModel> = ArrayList()

  fun clear() {
    cache.clear()
  }

  fun addAll(styles: List<StyleViewModel>) {
    cache.addAll(styles)
  }
  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    val style = cache.get(position)
    holder?.render(style, listener)
  }

  override fun getItemCount(): Int {
    return cache.size
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val holderView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_style, parent, false)
    return ViewHolder(holderView)
  }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val shortNameView = view.findViewById(R.id.shortName) as AppCompatTextView
    private val nameView = view.findViewById(R.id.name) as AppCompatTextView
    private val descriptionView = view.findViewById(R.id.description) as AppCompatTextView

    fun render(style: StyleViewModel, listener: OnStyleClickListener) {
      shortNameView.text = style.shortName
      nameView.text = style.name
      descriptionView.text = style.description
      itemView.setOnClickListener { listener?.onStyleClick(style) }
    }
  }

  interface OnStyleClickListener {
    fun onStyleClick(style: StyleViewModel)
  }
}