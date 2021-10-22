package com.e.buynow.view_model

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.buynow.model.CategoriesData
import com.e.buynow.model.ProductData
import com.e.buynow.network.api.Result
import com.e.buynow.repository.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val categoriesRepository: CategoriesRepository):ViewModel() {
    private var liveData: MutableLiveData<Result<CategoriesData>> = MutableLiveData()
    private var mediatorLiveData = MediatorLiveData<Result<CategoriesData>>()


    suspend fun fetchCategories(token:String){
        viewModelScope.launch {

            liveData = categoriesRepository.getCategories(token)
            mediatorLiveData.addSource(liveData){values: Result<CategoriesData> ->mediatorLiveData.value = values}
        }
    }


    fun getMediatorLiveData(): MediatorLiveData<Result<CategoriesData>> {
        return  mediatorLiveData
    }
}