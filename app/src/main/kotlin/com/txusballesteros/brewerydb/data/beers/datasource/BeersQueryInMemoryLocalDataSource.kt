package com.txusballesteros.brewerydb.data.beers.datasource

import com.txusballesteros.brewerydb.data.model.BeersQueryDataModel
import javax.inject.Inject

class BeersQueryInMemoryLocalDataSource @Inject constructor(): BeersQueryLocalDataSource {
  private var query: BeersQueryDataModel = BeersQueryDataModel(1)

  override fun getQuery(): BeersQueryDataModel {
    return query
  }

  override fun storeQuery(query: BeersQueryDataModel) {
    this.query = query
  }
}