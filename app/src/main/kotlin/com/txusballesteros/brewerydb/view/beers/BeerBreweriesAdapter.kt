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

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.extesion.inflate
import com.txusballesteros.brewerydb.presentation.model.BreweryViewModel
import kotlinx.android.synthetic.main.item_beer_brewery.view.*
import java.util.*

class BeerBreweriesAdapter(private val onBreweryClick: (BreweryViewModel) -> Unit):
      RecyclerView.Adapter<BeerBreweriesAdapter.ViewHolder>() {
  private val cache: MutableList<BreweryViewModel> = ArrayList()

  fun clear() {
    cache.clear()
  }

  fun addAll(breweries: List<BreweryViewModel>) {
    cache.addAll(breweries)
  }

  override fun getItemCount(): Int = cache.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
      = ViewHolder(parent.inflate(R.layout.item_beer_brewery))

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.itemView) {
    val brewery = cache[position]
    name.text = brewery.name
    description.text = brewery.description
    setOnClickListener { onBreweryClick(brewery) }
  }

  class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}