package com.e.buynow.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/*"data": {
    "emailVerified": false,
    "created": "2021-05-13T17:15:35.276Z",
    "_id": "609d6055a39ec5462bab5a72",
    "email": "riskrisky@gmail.com",
    "username": "riskrisky",
    "lastName": "Odegbagi",
    "firstName": "Odesare",
    "__v": 0,
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MDlkNjA1NWEzOWVjNTQ2MmJhYjVhNzIiLCJpYXQiOjE2MjA5MjY1NTB9.cUZJRh0rXnDL_iXMDPNqEXcXHz3QAVk5IrSq4aRod2g"
  },
  "status": "successful",
  "message": "Sign up successful"*/

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
    var _id:String
)

//data class Error()
