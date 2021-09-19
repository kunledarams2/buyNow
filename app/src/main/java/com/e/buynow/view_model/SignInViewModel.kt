package com.e.buynow.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.e.buynow.BuyNowApplication
import com.e.buynow.repository.ProductRepository

class SignInViewModel(val app: Application): AndroidViewModel(app) {

    private val repo: ProductRepository

    init {
        repo = (app as BuyNowApplication).repository
    }


}