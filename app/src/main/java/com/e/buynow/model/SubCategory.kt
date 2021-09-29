package com.e.buynow.model

import com.google.gson.annotations.SerializedName

data class SubCategory(@SerializedName("_id")val _id:String,
                       @SerializedName("name")val name:String,
                       @SerializedName("categoryId")val categoryId:Categories){
}