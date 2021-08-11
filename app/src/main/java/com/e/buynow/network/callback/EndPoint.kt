package com.e.buynow.network.callback

import com.e.buynow.network.api.URLS
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface EndPoint {

    @FormUrlEncoded
    @Headers("Accept:application/json", "Content-Type:application/x-www-form-urlencoded")
    @POST(URLS.createAccount)
    suspend fun createUser(
           @FieldMap  hashMap: HashMap<String, Any>
    ): Response<ResponseBody>

    @FormUrlEncoded
    @Headers("Accept:application/json", "Content-Type:application/x-www-form-urlencoded")
    @POST(URLS.login)
    suspend fun loginUser(
        @FieldMap  hashMap: HashMap<String, Any>
    ): Response<ResponseBody>
}