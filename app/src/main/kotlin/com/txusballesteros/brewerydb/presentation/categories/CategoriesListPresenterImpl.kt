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
package com.txusballesteros.brewerydb.presentation.categories

import com.txusballesteros.brewerydb.domain.model.Category
import com.txusballesteros.brewerydb.domain.usecase.UseCaseCallback
import com.txusballesteros.brewerydb.domain.usecase.categories.GetCategoriesUseCase
import com.txusballesteros.brewerydb.exception.ApplicationException
import com.txusballesteros.brewerydb.navigation.Navigator
import com.txusballesteros.brewerydb.presentation.AbsPresenter
import com.txusballesteros.brewerydb.presentation.model.CategoryViewModel
import com.txusballesteros.brewerydb.presentation.model.CategoryViewModelMapper
import javax.inject.Inject

class CategoriesListPresenterImpl @Inject constructor(private val getCategoriesUseCase: GetCategoriesUseCase,
                                                      private val mapper: CategoryViewModelMapper,
                                                      private val navigator: Navigator):
                                  AbsPresenter<CategoriesListPresenter.View>(), CategoriesListPresenter {

  override fun onRequestCategories() {
    getCategoriesUseCase.execute(object: UseCaseCallback<List<Category>>() {
      override fun onResult(result: List<Category>) {
        val categories = mapper.map(result)
        getView()?.renderCategories(categories)
      }

      override fun onError(error: ApplicationException) {
        getView()?.renderError()
      }
    })
  }

  override fun onCategoryClick(category: CategoryViewModel) {
    navigator.navigateToStylesList(getView(), category.id)
  }
}