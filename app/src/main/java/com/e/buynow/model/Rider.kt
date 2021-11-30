package com.e.buynow.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rider(
    @Json(name = "status")
    val status : String,
    @Json(name = "message")
    val message : String,
    @Json(name = "data")
    val data: RiderData


)

@JsonClass(generateAdapter = true)
data class RiderData(
    @Json(name = "token")
    val token : String,
    @Json(name = "id")
    val id : String,
    @Json(name = "lastName")
    val lastName : String,
    @Json(name = "firstName")
    val firstName : String,
    @Json(name = "phoneNumber")
    val phoneNumber : String,
    @Json(name = "rating")
    val rating : String,
)