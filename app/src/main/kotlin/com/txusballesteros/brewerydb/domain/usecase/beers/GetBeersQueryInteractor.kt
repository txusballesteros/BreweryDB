package com.txusballesteros.brewerydb.domain.usecase.beers

import com.txusballesteros.brewerydb.domain.model.BeersQuery
import com.txusballesteros.brewerydb.domain.repository.BeersQueryRepository
import com.txusballesteros.brewerydb.domain.repository.Repository
import com.txusballesteros.brewerydb.domain.usecase.UseCaseCallback
import com.txusballesteros.brewerydb.exception.ApplicationException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class GetBeersQueryInteractor @Inject constructor(private val executor: ExecutorService,
                                                  private val repository: BeersQueryRepository): GetBeersQueryUseCase {
  override fun execute(callback: UseCaseCallback<BeersQuery>) {
    doAsync(executorService = executor) {
      try {
        repository.getQuery(object : Repository.RepositoryCallback<BeersQuery> {
          override fun onResult(result: BeersQuery) {
            uiThread {
              callback.onResult(result)
            }
          }
        })
      } catch (error: ApplicationException) {
        uiThread {
          callback.onError(error)
        }
      }
    }
  }
}