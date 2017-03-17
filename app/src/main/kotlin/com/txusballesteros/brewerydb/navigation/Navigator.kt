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
package com.txusballesteros.brewerydb.navigation

import com.txusballesteros.brewerydb.navigation.commands.*
import com.txusballesteros.brewerydb.presentation.Presenter
import com.txusballesteros.brewerydb.presentation.model.IngredientTypeViewModel
import com.txusballesteros.brewerydb.view.AbsFragment
import javax.inject.Inject

class Navigator @Inject constructor() {
  fun navigateToStylesList(from: Presenter.View?, categoryId: Int) {
    val navigationCommand = StylesListNavigationCommand(categoryId)
    navigate(from, navigationCommand)
  }

  fun navigateToBeersList(from: Presenter.View?) {
    val navigationCommand = BeersListNavigationCommand()
    navigate(from, navigationCommand)
  }

  fun navigateToBeerDetail(from: Presenter.View?, beerId: String) {
    val navigationCommand = BeerDetailNavigationCommand(beerId)
    navigate(from, navigationCommand)
  }

  fun navigateToIngredientDetail(from: Presenter.View?, ingredientId: Int, ingredientType: IngredientTypeViewModel) {
    val navigationCommand = IngredientDetailNavigationCommand(ingredientId, ingredientType)
    navigate(from, navigationCommand)
  }

  fun navigateToBreweryDetail(from: Presenter.View?, breweryId: String) {
    val navigationCommand = BreweryDetailNavigationCommand(breweryId)
    navigate(from, navigationCommand)
  }

  fun navigateToUrl(from: Presenter.View?, url: String) {
    val navigationCommand = UrlNavigationCommand(url)
    navigate(from, navigationCommand)
  }

  private fun navigate(from: Presenter.View?, command: NavigationCommand) {
    if (from is AbsFragment) {
      val intent = command.build(from.activity)
      from.startActivity(intent)
    }
  }
}