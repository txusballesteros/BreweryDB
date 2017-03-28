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

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.domain.model.BeerViewModel
import com.txusballesteros.brewerydb.instrumentation.ImageDownloader
import org.jetbrains.anko.find
import java.util.*

class BeerListAdapter(private val listener: OnBeerClickListener,
                      private val imageDownloader: ImageDownloader) : RecyclerView.Adapter<BeerListAdapter.ViewHolder>() {
  private val cache: MutableList<BeerViewModel> = ArrayList()

  fun clear() {
    cache.clear()
  }

  fun addAll(beers: List<BeerViewModel>) {
    cache.addAll(beers)
  }

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    val beer = cache[position]
    holder?.render(beer, listener)
  }

  override fun getItemCount(): Int {
    return cache.size
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val holderView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_beer, parent, false)
    return ViewHolder(holderView, imageDownloader)
  }

  class ViewHolder(view: View, private val imageDownloader: ImageDownloader) : RecyclerView.ViewHolder(view) {
    private val labelView = view.find<AppCompatImageView>(R.id.label)
    private val displayNameView = view.find<AppCompatTextView>(R.id.displayName)
    private val descriptionView = view.find<AppCompatTextView>(R.id.description)

    fun render(beer: BeerViewModel, listener: OnBeerClickListener) {
      displayNameView.text = beer.displayName
      if (beer.description != null) {
        descriptionView.visibility = View.VISIBLE
        descriptionView.text = beer.description
      } else {
        descriptionView.visibility = View.GONE
      }
      itemView.setOnClickListener { listener.onBeerClick(beer) }
      renderLabel(beer)
    }

    fun renderLabel(beer: BeerViewModel) {
      if (beer.label != null && beer.label.medium != null) {
        labelView.visibility = View.VISIBLE
        imageDownloader.download(beer.label.medium, labelView)
      } else {
        labelView.visibility = View.GONE
      }
    }
  }

  interface OnBeerClickListener {
    fun onBeerClick(beer: BeerViewModel)
  }
}