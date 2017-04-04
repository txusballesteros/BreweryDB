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
package com.txusballesteros.brewerydb.instrumentation

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PicassoImageDownloader @Inject constructor(): ImageDownloader {
  override fun download(thumbnail: String?, imageUrl: String, view: ImageView) {
    if (thumbnail != null) {
      Picasso.with(view.context)
          .load(thumbnail)
          .into(view, object: Callback {
            override fun onSuccess() {
              download(imageUrl, view)
            }

            override fun onError() { }
          })
    } else {
      download(imageUrl, view)
    }
  }

  private fun download(imageUrl: String, view: ImageView) {
    Picasso.with(view.context)
        .load(imageUrl)
        .into(view)
  }
}