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
package com.txusballesteros.brewerydb.view.categories

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.model.CategoryViewModel
import org.jetbrains.anko.find
import java.util.*

class CategoriesListAdapter(private val listener: OnCategoryClickListener):
                      RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {
  private val cache: MutableList<CategoryViewModel> = ArrayList()

  fun clear() {
    cache.clear()
  }

  fun addAll(styles: List<CategoryViewModel>) {
    cache.addAll(styles)
  }

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    val category = cache.get(position)
    holder?.render(category, listener)
  }

  override fun getItemCount(): Int {
    return cache.size
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val holderView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_category, parent, false)
    return ViewHolder(holderView)
  }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val nameView = view.find<AppCompatTextView>(R.id.name)

    fun render(category: CategoryViewModel, listener: OnCategoryClickListener) {
      nameView.text = category.name
      itemView.setOnClickListener { listener.onCategoryClick(category) }
    }
  }

  interface OnCategoryClickListener {
    fun onCategoryClick(category: CategoryViewModel)
  }
}