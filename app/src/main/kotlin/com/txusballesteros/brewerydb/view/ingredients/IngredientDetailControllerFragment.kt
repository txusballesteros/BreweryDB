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
import android.view.MenuItem
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.presentation.ingredients.IngredientDetailControllerPresenter
import com.txusballesteros.brewerydb.presentation.model.IngredientTypeViewModel
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.ToolbarBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import org.jetbrains.anko.support.v4.withArguments
import javax.inject.Inject

class IngredientDetailControllerFragment: AbsFragment(), IngredientDetailControllerPresenter.View {
  companion object {
    val EXTRA_INGREDIENT_ID = "extra:ingredientId"
    val EXTRA_INGREDIENT_TYPE = "extra:ingredientType"

    fun newInstance(ingredientId: Int, ingredientType: IngredientTypeViewModel): IngredientDetailControllerFragment {
      return IngredientDetailControllerFragment().withArguments(
          EXTRA_INGREDIENT_ID to ingredientId,
          EXTRA_INGREDIENT_TYPE to ingredientType
      )
    }
  }

  @Inject lateinit var presenter: IngredientDetailControllerPresenter
  @Inject lateinit var toolbarBehaviour: ToolbarBehaviour
  @Inject lateinit var fragmentFactory: IngredientDetailControllerFragmentFactory

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_ingredient_detail_controller
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

  override fun onRequestViewBehaviours() {
    toolbarBehaviour.inject(activity, true)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when(item?.itemId) {
      android.R.id.home -> consume { closeView() }
      else -> super.onOptionsItemSelected(item)
    }
  }

  private fun closeView() {
    activity.finish()
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    val ingredientId = getIngredientId()
    val ingredientType = getIngredientType()
    presenter.onRequestIngredient(ingredientId, ingredientType)
  }

  private fun getIngredientId(): Int {
    return arguments.getInt(EXTRA_INGREDIENT_ID)
  }

  private fun getIngredientType(): IngredientTypeViewModel {
    return arguments.getSerializable(EXTRA_INGREDIENT_TYPE) as IngredientTypeViewModel
  }

  override fun renderIngredient(ingredientId: Int, type: IngredientTypeViewModel) {
    val fragment = fragmentFactory.getFragment(childFragmentManager, ingredientId, type)
    addFragment(fragment)
  }

  private fun addFragment(fragment: AbsFragment?) {
    if (fragment != null) {
      val tag = fragment::class.java.name
      childFragmentManager
          .beginTransaction()
          .replace(R.id.content, fragment, tag)
          .commitAllowingStateLoss()
    }
  }
}