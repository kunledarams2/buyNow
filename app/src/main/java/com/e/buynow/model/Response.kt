package com.e.buynow.model

import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("data")val data:Any,
                    @SerializedName("status")val status:String,
                    @SerializedName("message")val mesage:String)