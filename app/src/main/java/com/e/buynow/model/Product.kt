package com.e.buynow.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


/*
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
}*/



@JsonClass(generateAdapter = true)
data class Product (
    @Json(name = "data")
    val data: List<ProductData>,
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
data class ProductData (

    @Json(name = "tags")
    val tags: List<String>,
    @Json(name = "images")
    val images: List<String>,
    @Json(name = "active")
    val active: Boolean,
    @Json(name = "created")
    val created: String,
    @Json(name = "sizes")
    val sizes: List<String>,
    @Json(name = "colors")
    val colors: List<String>,

    @Json(name="_id")
    val _id: String,

  /*  @Json(name="storeId")
    val storeId: String,*/
    @Json(name="name")
    val name: String,
    @Json(name="price")
    val price: Long,
    @Json(name="units")
    val units: Long,
    /*@Json(name="category")
    val category: String,*/
    @Json(name="description")
    val description: String,
    @Json(name="status")
    val status: String,

   /* @Json(name="employeeId")
    val employeeId: String,*/


  /*  @Json(name="categoryId")
    val categoryId: CategoryID,*/

   /* val brand: String? = null,
    val material: String? = null,
    val style: String? = null,
    val type: String? = null*/


):Parcelable

/*
data class CategoryID (
    val created: String,

    @Json("_id")
    val id: CategoryIDID,

    val name: CategoryIDName,

    val image: String
)*/

