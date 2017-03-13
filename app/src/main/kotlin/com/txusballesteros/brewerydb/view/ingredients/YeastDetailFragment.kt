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
import com.txusballesteros.brewerydb.data.model.YeastViewModel
import com.txusballesteros.brewerydb.presentation.ingredients.IngredientDetailPresenter
import com.txusballesteros.brewerydb.presentation.model.IngredientTypeViewModel
import com.txusballesteros.brewerydb.presentation.model.IngredientViewModel
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.ErrorBehaviour
import com.txusballesteros.brewerydb.view.behaviours.LoadingBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_yeast_detail.*
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class YeastDetailFragment: AbsFragment(), IngredientDetailPresenter.View {
  companion object {
    val EXTRA_INGREDIENT_ID = "extra:ingredientId"

    fun newInstance(ingredientId: Int): YeastDetailFragment {
      return YeastDetailFragment().withArguments(
          EXTRA_INGREDIENT_ID to ingredientId
      )
    }
  }

  @Inject lateinit var presenter: IngredientDetailPresenter
  @Inject lateinit var loadingBehaviour: LoadingBehaviour
  @Inject lateinit var errorBehaviour: ErrorBehaviour

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_yeast_detail
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
    errorBehaviour.inject(activity)
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    val ingredientId = getIngredientId()
    presenter.onRequestIngredient(ingredientId, IngredientTypeViewModel.YEAST)
  }

  private fun getIngredientId(): Int {
    return arguments.getInt(EXTRA_INGREDIENT_ID)
  }

  override fun renderIngredient(ingredient: IngredientViewModel) {
    if (ingredient is YeastViewModel) {
      name.text = ingredient.name
      description.text = ingredient.description ?: "NA"
      yeastType.text = ingredient.yeastType ?: "-"
      yeastFormat.text = ingredient.yeastFormat ?: "-"
      attenuationMin.text = toString(ingredient.attenuationMin) ?: "-"
      attenuationMax.text = toString(ingredient.attenuationMax) ?: "-"
      fermentTempMin.text = toString(ingredient.fermentTempMin) ?: "-"
      fermentTempMax.text = toString(ingredient.fermentTempMax) ?: "-"
      alcoholToleranceMin.text = toString(ingredient.alcoholToleranceMin) ?: "-"
      alcoholToleranceMax.text = toString(ingredient.alcoholToleranceMax) ?: "-"
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
    errorBehaviour.hideError()
    loadingBehaviour.showLoading()
  }

  override fun hideLoading() {
    loadingBehaviour.hideLoading()
  }

  override fun renderError() {
    errorBehaviour.showError()
  }
}