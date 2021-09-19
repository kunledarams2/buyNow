package com.e.buynow.model

import com.google.gson.annotations.SerializedName

data class Categories (@SerializedName("_id")
                       val _id:String,
                       @SerializedName("name")
                       val name:String){
}