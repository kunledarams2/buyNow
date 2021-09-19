package com.e.buynow.model

import com.google.gson.annotations.SerializedName

data class Deals(@SerializedName("status") val status:String,
                @SerializedName("created")val created:String,
                @SerializedName("_id")val _id:String,
                @SerializedName("productId")val productId:String,
@SerializedName("coupon") val coupon:String,
@SerializedName("discount") val discount:Int,
@SerializedName ("numberUpForSale")val numberUpForSale:Int,
@SerializedName("product") val product: Product) {
}