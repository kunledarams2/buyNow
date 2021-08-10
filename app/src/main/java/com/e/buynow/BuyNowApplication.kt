package com.e.buynow

import android.app.Activity
import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BuyNowApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }

    operator fun get(activity: Activity): BuyNowApplication? {
        return activity.application as BuyNowApplication
    }
}