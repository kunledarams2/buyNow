package com.e.buynow.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Product (@SerializedName("tag") val tag:Array<String>,
                    @SerializedName("images")
                    val images:Array<String>,
                    @SerializedName("sizes")
                    val sizes: Array<Int>,
                    @SerializedName("colors")val colors:Array<String>,
                    @SerializedName("active")
                    val active:Boolean,
                    @SerializedName("_id")
                    val _id:String,
                    @SerializedName("storeId")
                    val storeId:String,
                    @SerializedName("name")
                    val name:String,
                    @SerializedName("tags")
                    val tags:String,
                    @SerializedName("price")
                    val price:Float,
                    @SerializedName("units")
                    val units:Int,
                    @SerializedName("categories")
                    val categories:String,
                    @SerializedName("description")
                    val description:String,
                    @SerializedName("status")
                    val status:String,){
}