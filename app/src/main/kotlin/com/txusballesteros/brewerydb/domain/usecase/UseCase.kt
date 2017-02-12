package com.txusballesteros.brewerydb.domain.usecase

import org.jetbrains.anko.doAsync
import java.util.concurrent.ExecutorService

abstract class UseCase(private val executor: ExecutorService) {

  fun execute() {
    doAsync(executorService = executor) {
      onExecute()
    }
  }

  abstract fun onExecute()
}