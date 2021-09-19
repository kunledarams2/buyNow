package com.e.buynow.repository

import kotlinx.coroutines.CoroutineScope

abstract class LoginRepository (protected open val scope: CoroutineScope){




    suspend fun login(){

    }
}