package com.txusballesteros.brewerydb.domain.usecase.categories

import com.txusballesteros.brewerydb.domain.model.Category
import com.txusballesteros.brewerydb.domain.repository.CategoriesRepository
import com.txusballesteros.brewerydb.domain.repository.Repository
import com.txusballesteros.brewerydb.domain.usecase.UseCaseCallback
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class GetCategoriesInteractor @Inject constructor(private val executorService: ExecutorService,
                                                  private val repository: CategoriesRepository):
                              GetCategoriesUseCase {

  override fun execute(callback: UseCaseCallback<List<Category>>) {
    doAsync(executorService = executorService) {
      repository.getCategories(object: Repository.RepositoryCallback<List<Category>> {
        override fun onResult(result: List<Category>) {
          uiThread {
            callback.onResult(result)
          }
        }
      })
    }
  }
}