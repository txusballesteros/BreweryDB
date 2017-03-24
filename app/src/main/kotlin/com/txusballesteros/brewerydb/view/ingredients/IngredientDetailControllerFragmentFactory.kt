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

import android.support.v4.app.FragmentManager
import com.txusballesteros.brewerydb.presentation.model.IngredientTypeViewModel
import com.txusballesteros.brewerydb.view.AbsFragment
import javax.inject.Inject

class IngredientDetailControllerFragmentFactory @Inject constructor() {

  fun getFragment(fragmentManager: FragmentManager,
                                  ingredientId: Int,
                                  ingredientType: IngredientTypeViewModel): AbsFragment? {
    var result: AbsFragment?
    when(ingredientType) {
      IngredientTypeViewModel.HOP -> result = getHopDetailFragment(fragmentManager, ingredientId)
      IngredientTypeViewModel.FERMENTABLE -> result = getFermentableDetailFragment(fragmentManager, ingredientId)
      IngredientTypeViewModel.YEAST -> result = getYeastDetailFragment(fragmentManager, ingredientId)
      else -> result = null
    }
    return result
  }

  private fun getHopDetailFragment(fragmentManager: FragmentManager, ingredientId: Int): AbsFragment {
    val tag = HopDetailFragment::class.java.name
    val fragment = fragmentManager.findFragmentByTag(tag) ?: HopDetailFragment.newInstance(ingredientId)
    return fragment as AbsFragment
  }

  private fun getFermentableDetailFragment(fragmentManager: FragmentManager, ingredientId: Int): AbsFragment {
    val tag = FermentableDetailFragment::class.java.name
    val fragment = fragmentManager.findFragmentByTag(tag) ?: FermentableDetailFragment.newInstance(ingredientId)
    return fragment as AbsFragment
  }

  private fun getYeastDetailFragment(fragmentManager: FragmentManager, ingredientId: Int): AbsFragment {
    val tag = YeastDetailFragment::class.java.name
    val fragment = fragmentManager.findFragmentByTag(tag) ?: YeastDetailFragment.newInstance(ingredientId)
    return fragment as AbsFragment
  }
}