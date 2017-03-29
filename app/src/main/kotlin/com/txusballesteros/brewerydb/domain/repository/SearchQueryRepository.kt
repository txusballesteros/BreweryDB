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
package com.txusballesteros.brewerydb.domain.repository

import com.txusballesteros.brewerydb.data.model.SearchQueryDataModelMapper
import com.txusballesteros.brewerydb.data.search.strategy.ClearSearchQueryStrategy
import com.txusballesteros.brewerydb.data.search.strategy.GetSearchQueryStrategy
import com.txusballesteros.brewerydb.data.search.strategy.StoreSearchQueryStrategy
import com.txusballesteros.brewerydb.domain.model.SearchQuery
import com.txusballesteros.brewerydb.domain.reactive.Observer
import com.txusballesteros.brewerydb.domain.reactive.Subject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchQueryRepository @Inject constructor(private val getSearchQueryStrategy: GetSearchQueryStrategy.Builder,
                                                private val storeSearchQueryStrategy: StoreSearchQueryStrategy.Builder,
                                                private val clearSearchQueryStrategy: ClearSearchQueryStrategy.Builder,
                                                private val subject: Subject,
                                                private val mapper: SearchQueryDataModelMapper) {

  fun get(onResult: (SearchQuery) -> Unit) {
    getSearchQueryStrategy.build().execute(onResult = {
      val query = mapper.map(it!!)
      onResult(query)
    })
  }

  fun subscribe(): Observer {
    return subject.asObserver()
  }

  fun clear(onSuccess: () -> Unit) {
    clearSearchQueryStrategy.build().execute {
      subject.onNext()
      onSuccess()
    }
  }

  fun store(query: SearchQuery, onResult: () -> Unit) {
    val dataQuery = mapper.map(query)
    storeSearchQueryStrategy.build().execute(dataQuery, onResult = {
      subject.onNext()
      onResult()
    })
  }
}