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
package com.txusballesteros.brewerydb.navigation.commands

import android.content.Context
import android.content.Intent
import com.txusballesteros.brewerydb.view.styles.StylesListActivity
import org.jetbrains.anko.intentFor

class StylesListNavigationCommand constructor(val categoryId: Int): NavigationCommand() {
  override fun onRequestIntent(context: Context): Intent {
    return context.intentFor<StylesListActivity>(StylesListActivity.EXTRA_CATEGORY_ID to categoryId)
  }
}