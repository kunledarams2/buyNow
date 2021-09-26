package com.e.buynow.network

import com.e.buynow.network.callback.EndPoint
import javax.inject.Inject

class NetworkInteraction @Inject constructor(private val endPoint: EndPoint) {


    suspend fun login(params:HashMap<String, Any>) = endPoint.loginUser(params)

    suspend fun getDeals(apikey: String){
        endPoint.fetchDeals(apikey)
    }
}