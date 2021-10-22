package com.e.buynow.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
data class Categories (
        @Json(name = "data")
        val data: List<CategoriesData>,
        @Json(name = "status")
        val status: String,
        @Json(name = "message")
        val message: String,
        @Json(name = "page")
        val page: Long,
        @Json(name = "length")
        val length: Long
)

@Parcelize
@JsonClass(generateAdapter = true)
data class CategoriesData (
        @Json(name = "created")
        val created: String,
        @Json(name = "_id")
        val _id: String,
        @Json(name = "name")
        val name: String,

        @Json(name = "image")
        val image: String
):Parcelable