package com.txusballesteros.brewerydb.view.behaviour

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.txusballesteros.brewerydb.R
import org.jetbrains.anko.find
import javax.inject.Inject

class LoadingBehaviour @Inject constructor(): Behaviour() {
  lateinit var contentHolder: View
  lateinit var loadingHolder: View
  lateinit var activity : AppCompatActivity

  override fun inject(activity: Activity) {
    if (activity is AppCompatActivity) {
      this.activity = activity
    }
    super.inject(activity)
  }

  override fun onRequestPlaceHolderId(): Int {
    return R.id.loading_place_holder
  }

  override fun onRequestLayoutResourceId(): Int {
    return R.layout.loading
  }

  fun showLoading() {
    contentHolder.visibility = View.GONE
    loadingHolder.visibility = View.VISIBLE
  }

  fun hideLoading() {
    contentHolder.visibility = View.VISIBLE
    loadingHolder.visibility = View.GONE
  }

  override fun onBehaviorReady(view: View) {
    this.loadingHolder = activity.find<View>(R.id.loadingHolder)
    this.contentHolder = activity.find<View>(R.id.content_place_holder)
  }
}