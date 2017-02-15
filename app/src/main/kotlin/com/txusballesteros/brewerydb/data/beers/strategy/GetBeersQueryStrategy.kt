package com.txusballesteros.brewerydb.data.beers.strategy

import com.txusballesteros.brewerydb.data.beers.datasource.BeersQueryLocalDataSource
import com.txusballesteros.brewerydb.data.model.BeersQueryDataModel
import com.txusballesteros.brewerydb.data.strategy.LocalStrategy
import javax.inject.Inject

class GetBeersQueryStrategy private constructor(private val localDataSource: BeersQueryLocalDataSource):
                                      LocalStrategy<Void, BeersQueryDataModel>() {
  override fun onRequestCallToLocal(params: Void?): BeersQueryDataModel? {
    return localDataSource.getQuery()
  }

  class Builder @Inject constructor(private val localDataSource: BeersQueryLocalDataSource){
    fun build() = GetBeersQueryStrategy(localDataSource)
  }
}