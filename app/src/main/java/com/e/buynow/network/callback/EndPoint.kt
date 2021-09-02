package com.e.buynow.network.callback

import com.android_dr_app.network.NetworkResponse
import com.e.buynow.model.User
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
    ): NetworkResponse<User, User>

    @FormUrlEncoded
    @Headers("Accept:application/json", "Content-Type:application/x-www-form-urlencoded")
    @POST(URLS.login)
    suspend fun loginUser(
        @FieldMap  hashMap: HashMap<String, Any>
    ): NetworkResponse<User, User>

    @FormUrlEncoded
    @Headers("Accept:application/json", "Content-Type:application/x-www-form-urlencoded")
    @PUT(URLS.createAccount)
    suspend fun updateUserInfo(
        @FieldMap  hashMap: HashMap<String, Any>
    ): NetworkResponse<User, User>
}