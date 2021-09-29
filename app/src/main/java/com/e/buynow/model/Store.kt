package com.e.buynow.model

import com.google.gson.annotations.SerializedName

data class Store(@SerializedName("location")val location: Location,
                 @SerializedName("status")val status:String,
                 @SerializedName("_id")val _id:String,
                 @SerializedName("name")val name:String,
                 @SerializedName("email")val email:String,
                 @SerializedName("address")val address:String,
                 @SerializedName("phone")val phone:String,

) {
}