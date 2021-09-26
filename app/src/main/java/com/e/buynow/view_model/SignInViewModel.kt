package com.e.buynow.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.e.buynow.BuyNowApplication
import com.e.buynow.repository.ProductRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch

class SignInViewModel(val app: Application): AndroidViewModel(app) {

    //private val repo: ProductRepository

    init {

    }


    fun signInUser(params:HashMap<String, String>){
        viewModelScope.launch {

        }
    }

}