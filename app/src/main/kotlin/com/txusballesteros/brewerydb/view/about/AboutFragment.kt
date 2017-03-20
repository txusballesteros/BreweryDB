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
package com.txusballesteros.brewerydb.view.about

import android.os.Bundle
import android.view.MenuItem
import com.txusballesteros.brewerydb.BuildConfig
import com.txusballesteros.brewerydb.R
import com.txusballesteros.brewerydb.view.AbsFragment
import com.txusballesteros.brewerydb.view.behaviours.ToolbarBehaviour
import com.txusballesteros.brewerydb.view.di.ViewComponent
import kotlinx.android.synthetic.main.fragment_about.*
import javax.inject.Inject

class AboutFragment: AbsFragment() {
  companion object {
    fun newInstance() = AboutFragment()
  }

  @Inject lateinit var toolbarBehaviour: ToolbarBehaviour

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.fragment_about
  }

  override fun onPresenterShouldBeAttached() { }

  override fun onPresenterShouldBeDetached() { }

  override fun onRequestInjection(viewComponent: ViewComponent) {
    viewComponent.inject(this)
  }

  override fun onRequestViewBehaviours() {
    toolbarBehaviour.inject(activity, true)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    var result: Boolean = true
    when(item?.itemId) {
      android.R.id.home -> closeView()
      else -> result = super.onOptionsItemSelected(item)
    }
    return result
  }

  override fun onViewReady(savedInstanceState: Bundle?) {
    val versionText = "v" + BuildConfig.VERSION_NAME
    version.text = versionText
  }

  private fun closeView() {
    activity.finish()
  }
}