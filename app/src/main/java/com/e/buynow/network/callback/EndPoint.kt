package com.e.buynow.network.callback

import com.android_dr_app.network.NetworkResponse
import com.e.buynow.model.*
import com.e.buynow.network.api.URLS
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface EndPoint {

    @FormUrlEncoded
    @Headers("Accept:application/json", "Content-Type:application/x-www-form-urlencoded")
    @POST(URLS.createAccount)
    suspend fun createUser(@FieldMap  hashMap: HashMap<String, Any>): NetworkResponse<User, User>

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

    @GET(URLS.DEALS)
    suspend fun fetchDeals(@Header("Authorization") api:String):NetworkResponse<List<Deals>, Error>

    @Headers("Accept:application/json", "Content-Type:application/x-www-form-urlencoded")
    @GET(URLS.CATEGORIES)
    suspend fun fetchCategories(@Header("Authorization") api:String):
            NetworkResponse<Categories, Categories>

    @GET("${URLS.PRODUCT}/{id}")
    suspend fun fetchProductById(@Header("Authorization") api:String,
                                 @Path("id") id:String):NetworkResponse<Product,Error>


    @Headers("Accept:application/json", "Content-Type:application/x-www-form-urlencoded")
    @GET(URLS.PRODUCT)
    suspend fun fetchProduct(
        @Header("Authorization") token:String,
        @QueryMap hashMap: HashMap<String, Any>,
    ):NetworkResponse<Product,Product>

    @FormUrlEncoded
    @Headers("Accept:application/json", "Content-Type:application/x-www-form-urlencoded")
    @POST(URLS.loginRider)
    suspend fun loginRider(
        @FieldMap  hashMap: HashMap<String, Any>
    ):NetworkResponse<Rider, Rider>
}