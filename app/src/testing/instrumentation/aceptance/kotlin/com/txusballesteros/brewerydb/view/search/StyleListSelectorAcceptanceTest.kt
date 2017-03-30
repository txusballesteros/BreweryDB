package com.txusballesteros.brewerydb.view.search
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
import android.support.test.rule.ActivityTestRule
import com.txusballesteros.brewerydb.view.AcceptanceTest
import org.junit.Rule

class StyleListSelectorAcceptanceTest: AcceptanceTest() {
  @get:Rule val testRule = ActivityTestRule<SearchActivity>(SearchActivity::class.java)

//  @Test
//  fun shouldNavigateToBack() {
//    onView(withId(R.id.styleSelector)).perform(click())
//    onView(withText(R.string.styles)).perform(pressBack())
//  }
}