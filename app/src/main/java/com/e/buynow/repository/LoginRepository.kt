package com.e.buynow.repository

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope

@ActivityRetainedScoped
class LoginRepository (protected open val scope: CoroutineScope){




    suspend fun login(){

    }
}