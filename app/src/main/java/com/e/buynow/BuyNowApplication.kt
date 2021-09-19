package com.e.buynow

import android.app.Activity
import android.app.Application
import com.e.buynow.repository.ProductRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


@HiltAndroidApp
class BuyNowApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }

    operator fun get(activity: Activity): BuyNowApplication {
        return activity.application as BuyNowApplication
    }

    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    //val database by lazy { AlbumDatabase.getDatabase(this)}
    val repository by lazy { ProductRepository.getInstance(applicationScope) }
}