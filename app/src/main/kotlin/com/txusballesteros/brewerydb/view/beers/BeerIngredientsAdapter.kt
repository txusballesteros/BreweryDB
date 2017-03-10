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
package com.txusballesteros.brewerydb.view.beers

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.data.model.BeerIngredientViewModel
import org.jetbrains.anko.find
import java.util.*

class BeerIngredientsAdapter: RecyclerView.Adapter<BeerIngredientsAdapter.ViewHolder>() {
  private val cache: MutableList<BeerIngredientViewModel> = ArrayList()

  fun clear() {
    cache.clear()
  }

  fun addAll(styles: List<BeerIngredientViewModel>) {
    cache.addAll(styles)
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val holderView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_beer_ingredient, parent, false)
    return ViewHolder(holderView)
  }

  override fun getItemCount(): Int = cache.size

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    val ingredient = cache[position]
    holder?.render(ingredient)
  }

  class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val nameView = view.find<AppCompatTextView>(R.id.name)
    val categoryView = view.find<AppCompatTextView>(R.id.category)

    fun render(ingredient: BeerIngredientViewModel) {
      nameView.text = ingredient.name
      categoryView.text = ingredient.name
    }
  }
}