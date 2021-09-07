package com.e.buynow.util.interfaces

import kotlinx.coroutines.flow.Flow

interface DataStoreManager {

    suspend fun saveTokenToPreferencesStore(token:String)
    fun getTokenFromPreferencesStore():Flow<String>
}