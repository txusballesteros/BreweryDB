package com.txusballesteros.brewerydb.domain.usecase.beers

import com.txusballesteros.brewerydb.domain.model.BeersQuery
import com.txusballesteros.brewerydb.domain.repository.BeersQueryRepository
import com.txusballesteros.brewerydb.domain.repository.Repository
import com.txusballesteros.brewerydb.domain.usecase.UseCaseEmptyCallback
import com.txusballesteros.brewerydb.exception.ApplicationException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class StoreBeersQueryInteractor @Inject constructor(private val executor: ExecutorService,
                                                    private val repository: BeersQueryRepository): StoreBeersQueryUseCase {
  override fun execute(query: BeersQuery, callback: UseCaseEmptyCallback) {
    doAsync(executorService = executor) {
      try {
        repository.storeQuery(query, object: Repository.RepositoryEmptyCallback {
          override fun onResult() {
            callback.onResult()
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