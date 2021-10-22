package com.e.buynow.network.data

import androidx.room.*
import com.e.buynow.model.Cart
import kotlinx.coroutines.flow.Flow

/*import androidx.room.*
import com.e.buynow.model.Cart
import kotlinx.coroutines.flow.Flow*/

@Dao
interface CartDao {

    @Transaction
    @Query("SELECT * FROM Cart ORDER BY id DESC")
    fun getAllItem(): Flow<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem (cart: Cart):Long

    @Query("DELETE FROM Cart WHERE id =:id")
    suspend fun deleteSingleCartItem(id: Int)
}