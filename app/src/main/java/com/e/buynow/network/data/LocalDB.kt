package com.e.buynow.network.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.buynow.model.Cart

@Database(entities = [Cart::class], version = 1, exportSchema = false)
abstract  class LocalDB: RoomDatabase() {

    abstract fun cartDao(): CartDao
}
