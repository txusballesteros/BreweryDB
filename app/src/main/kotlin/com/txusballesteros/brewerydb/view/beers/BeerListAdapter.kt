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
import com.txusballesteros.brewerydb.domain.model.BeerViewModel
import com.txusballesteros.brewerydb.extension.download
import com.txusballesteros.brewerydb.extension.inflate
import com.txusballesteros.brewerydb.instrumentation.ImageDownloader
import kotlinx.android.synthetic.main.item_beer.view.*
import java.util.*

class BeerListAdapter(private val onBeerClick: (BeerViewModel, View) -> Unit,
                      private val imageDownloader: ImageDownloader) : RecyclerView.Adapter<BeerListAdapter.ViewHolder>() {
  private val cache: MutableList<BeerViewModel> = ArrayList()

  fun clear() {
    cache.clear()
  }

  fun addAll(beers: List<BeerViewModel>) {
    cache.addAll(beers)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
      = ViewHolder(parent.inflate(R.layout.item_beer))

  override fun onBindViewHolder(holder: ViewHolder, position: Int) = with(holder.itemView) {
    val beer =  cache[position]
    displayName.text = beer.displayName
    description.visibility = beer.description?.let {
      description.text = beer.description
      View.VISIBLE
    } ?: View.GONE
    if (beer.label != null) {
      label.download(imageDownloader =  imageDownloader, imageUrl =  beer.label?.medium)
    } else {
      label.setImageResource(R.drawable.beer_place_holder)
    }
    setOnClickListener { onBeerClick(beer, label) }
  }

  override fun getItemCount(): Int {
    return cache.size
  }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}