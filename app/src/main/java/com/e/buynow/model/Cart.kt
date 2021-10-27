package com.e.buynow.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Cart")
data class Cart(
        @PrimaryKey(autoGenerate = true)
        @NonNull
        val id: Int? =null,

        @ColumnInfo(name = "itemName")
        val itemName: String,
        @ColumnInfo(name = "itemPrice")
        val itemPrice: Int,
        @ColumnInfo(name = "itemId")
        val itemId: String,
        @ColumnInfo(name = "quantity")
        var quantity: Int,
        @ColumnInfo(name = "itemImagePath")
        val itemImagePath :String,
       /* val categoriesId :Int*/
)
