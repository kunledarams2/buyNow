package com.e.buynow.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass




@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "status")
    val status :String,
    @Json(name = "message")
    var message:String,
    @Json(name = "data")
    var data: Data


)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "token")
    var token:String,
    @Json(name = "email")
    var email:String,
    @Json(name = "_id")
    var _id:String,
    @Json(name = "username")
    var username:String,
    @Json(name = "lastName")
    var lastName:String,
    @Json(name = "firstName")
    var firstName:String,

)

//data class Error()
