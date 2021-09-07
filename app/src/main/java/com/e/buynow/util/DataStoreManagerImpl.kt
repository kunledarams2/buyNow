package com.e.buynow.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import com.e.buynow.model.LoginStatus
import com.e.buynow.util.interfaces.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStoreManagerImpl @Inject constructor(
    private val tokenPreferenceStore:DataStore<LoginStatus> ) :
    DataStoreManager {


    private val TOKEN = stringPreferencesKey("TOKEN")

    override suspend fun saveTokenToPreferencesStore() {
        tokenPreferenceStore.edit{ preferences -> preferences[TOKEN] = token }
    }

    override fun getTokenFromPreferencesStore(): Flow<String> {
        TODO("Not yet implemented")
    }
}