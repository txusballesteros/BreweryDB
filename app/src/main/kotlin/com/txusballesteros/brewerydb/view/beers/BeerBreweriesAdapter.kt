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
import com.txusballesteros.brewerydb.presentation.model.BreweryViewModel
import org.jetbrains.anko.find
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

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
    val holderView = LayoutInflater.from(parent!!.context).inflate(R.layout.item_beer_brewery, parent, false)
    return ViewHolder(holderView, onBreweryClick)
  }

  override fun getItemCount(): Int = cache.size

  override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
    val brewery = cache[position]
    holder?.render(brewery)
  }

  class ViewHolder(view: View, private val onBreweryClick: (BreweryViewModel) -> Unit): RecyclerView.ViewHolder(view) {
    val rootView = view.find<View>(R.id.root)
    val nameView = view.find<AppCompatTextView>(R.id.name)
    val descriptionView = view.find<AppCompatTextView>(R.id.description)

    fun render(brewery: BreweryViewModel) {
      nameView.text = brewery.name
      descriptionView.text = brewery.description
      rootView.setOnClickListener { onBreweryClick(brewery) }
    }
  }
}