package com.e.buynow.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android_dr_app.network.NetworkResponse
import com.e.buynow.model.CategoriesData
import com.e.buynow.model.ProductData
import com.e.buynow.network.api.Result
import com.e.buynow.network.callback.EndPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesRepository @Inject constructor(private val endPoint: EndPoint) {

    suspend fun getCategories( token: String): MutableLiveData<Result<CategoriesData>> {

        val result = Result<CategoriesData>()
        val data = MutableLiveData<Result<CategoriesData>>()

        CoroutineScope(Dispatchers.IO).launch {
            when(val response = endPoint.fetchCategories(token)){
                is NetworkResponse.Success->{
                    val productData=ArrayList<ProductData>()
                    Log.d("ProductRepos",response.body.toString())
                    if (response.body.data.isNotEmpty()){
                        result.setDataList(response.body.data)
                    }else result.setMessage(response.body.message)
                    withContext(Dispatchers.Main){
                        data.value = result
                    }

                }
                is  NetworkResponse.NetworkError->result.setMessage(response.error.message)
                is  NetworkResponse.ApiError-> Log.d("ProductRepos","AppiError......")
                else-> Log.d("ProductRepos","Unknown Error.....")
            }
        }


        return data
    }
}