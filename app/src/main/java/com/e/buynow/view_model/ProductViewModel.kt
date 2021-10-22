package com.e.buynow.view_model

import androidx.lifecycle.*
import com.e.buynow.model.ProductData
import com.e.buynow.network.api.Result
import com.e.buynow.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository):ViewModel() {



    private var liveData: MutableLiveData<Result<ProductData>> = MutableLiveData()
    private var mediatorLiveData = MediatorLiveData<Result<ProductData>>()


   suspend fun fetchAProduct(token:String){
        viewModelScope.launch {

            liveData = productRepository.getAllProduct(token)
            mediatorLiveData.addSource(liveData){values: Result<ProductData>->mediatorLiveData.value = values}
        }
    }


    fun getMediatorLiveData():MediatorLiveData<Result<ProductData>>{
        return  mediatorLiveData
    }
}