package com.e.buynow.repository

import android.util.Log
import kotlinx.coroutines.CoroutineScope

class ProductRepository(override val scope: CoroutineScope): LoginRepository(scope){

    companion object {
        // Singleton instantiation you already know and love
        @Volatile private var instance: ProductRepository? = null
        private const val TAG = "Repository"
        private const val NO_AUTH = "No Auth"
        const val KEY_PREF = "key"

        fun getInstance(scope: CoroutineScope):ProductRepository {
            val temp = instance
            if (temp!=null)return temp
            synchronized(this) {
                instance = ProductRepository(scope)
                Log.d(TAG, "getInstance: $instance")
                return instance!!
            }
        }
    }
}