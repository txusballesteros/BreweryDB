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
package com.txusballesteros.brewerydb.view.ingredients

import android.os.Bundle
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.ingredients.IngredientDetailPresenter
import com.txusballesteros.brewerydb.presentation.model.HopViewModel
import com.txusballesteros.brewerydb.presentation.model.IngredientTypeViewModel
import com.txusballesteros.brewerydb.presentation.model.IngredientViewModel
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.LoadingBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_hop_detail.*
import org.jetbrains.anko.support.v4.withArguments
import org.jetbrains.anko.toast
import javax.inject.Inject

class HopDetailFragment: AbsFragment(), IngredientDetailPresenter.View {
  companion object {
    val EXTRA_INGREDIENT_ID = "extra:ingredientId"

    fun newInstance(ingredientId: Int): HopDetailFragment {
      return HopDetailFragment().withArguments(
          EXTRA_INGREDIENT_ID to ingredientId
      )
    }
  }

  @Inject lateinit var presenter: IngredientDetailPresenter
  @Inject lateinit var loadingBehaviour: LoadingBehaviour

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_hop_detail
  }

  override fun onPresenterShouldBeAttached() {
    presenter.onAttachView(this)
  }

  override fun onPresenterShouldBeDetached() {
    presenter.onDetachView()
  }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onComposeView() {
    loadingBehaviour.inject(activity)
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    val ingredientId = getIngredientId()
    presenter.onRequestIngredient(ingredientId, IngredientTypeViewModel.HOP)
  }

  private fun getIngredientId(): Int {
    return arguments.getInt(EXTRA_INGREDIENT_ID)
  }

  override fun renderIngredient(ingredient: IngredientViewModel) {
    if (ingredient is HopViewModel) {
      name.text = ingredient.name
      description.text = ingredient.description ?: "NA"
      if (ingredient.country != null) {
        country.text = ingredient.country.displayName
      } else {
        country.text = "NA"
      }
      alphaAcidMin.text = toString(ingredient.alphaAcidMin) ?: "-"
      alphaAcidMax.text = toString(ingredient.alphaAcidMin) ?: "-"
      betaAcidMin.text = toString(ingredient.betaAcidMin) ?: "-"
      betaAcidMax.text = toString(ingredient.betaAcidMax) ?: "-"
      humuleneMin.text = toString(ingredient.humuleneMin) ?: "-"
      humuleneMax.text = toString(ingredient.humuleneMax) ?: "-"
      caryophylleneMin.text = toString(ingredient.caryophylleneMin) ?: "-"
      caryophylleneMax.text = toString(ingredient.caryophylleneMax) ?: "-"
      cohumuloneMin.text = toString(ingredient.cohumuloneMin) ?: "-"
      cohumuloneMax.text = toString(ingredient.cohumuloneMax) ?: "-"
      myrceneMin.text = toString(ingredient.myrceneMin) ?: "-"
      myrceneMax.text = toString(ingredient.myrceneMax) ?: "-"
      farneseneMin.text = toString(ingredient.farneseneMin) ?: "-"
      farneseneMax.text = toString(ingredient.farneseneMax) ?: "-"
    }
  }

  fun toString(value: Float?): String? {
    var result: String? = null
    if (value != null) {
      result = value.toString()
    }
    return result
  }

  override fun showLoading() {
    loadingBehaviour.showLoading()
  }

  override fun hideLoading() {
    loadingBehaviour.hideLoading()
  }

  override fun renderError() {
    activity.toast("Upss!!")
  }
}