package com.e.buynow.repository

import com.e.buynow.model.Cart
import com.e.buynow.network.data.LocalDB
//import com.e.buynow.network.data.LocalDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepository @Inject constructor(private val db: LocalDB) {

    private val cartDao = db.cartDao()

    suspend fun insertCart(cart: Cart):Long{
        return cartDao.insertCartItem(cart)
    }

    val getCartItem : Flow<List<Cart>> get() = cartDao.getAllItem()

    suspend fun deleteItem(id:Int){
        cartDao.deleteSingleCartItem(id)
    }
}
