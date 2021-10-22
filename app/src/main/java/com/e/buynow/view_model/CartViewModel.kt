package com.e.buynow.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.buynow.model.Cart
import com.e.buynow.repository.CartRepository
//import com.e.buynow.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) :ViewModel() {

    private val _response = MutableLiveData<Long>()
    val response: LiveData<Long> = _response

    suspend fun insertItem(cart: Cart){
        viewModelScope.launch {
        _response.postValue(cartRepository.insertCart(cart))
        }
    }
}