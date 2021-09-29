package com.e.buynow.model

import com.google.gson.annotations.SerializedName

data class Location(@SerializedName("type")val type:String,
                    @SerializedName("coordinates")val coordinates:Array<Int>) {
}