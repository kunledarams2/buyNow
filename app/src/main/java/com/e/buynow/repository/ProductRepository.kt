package com.e.buynow.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android_dr_app.network.NetworkResponse
import com.e.buynow.model.ProductData
import com.e.buynow.network.NetworkInteraction
import com.e.buynow.network.api.Result
import com.e.buynow.network.callback.EndPoint
import com.e.buynow.util.GeneralUtils
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


open class ProductRepository @Inject constructor( private val endPoint: EndPoint, private val context: Context){

     suspend fun  geteAllProduct(token:String): MutableLiveData<Result<ProductData>> {

        val result = Result<ProductData>()
        val liveData = MutableLiveData<Result<ProductData>>()
        val params= HashMap<String, Any>()

        params["page"] = 1
        params["limit"] = 10

        val productList = ArrayList<ProductData>()
         CoroutineScope(Dispatchers.IO).launch {

             when(val response = endPoint.fetchProduct(token,params)){
                 is NetworkResponse.Success->{

                     for (element in response.body.data){
                         productList.add(element)

                     }
                     result.setDataList(productList)
                     withContext(Dispatchers.Main){

                         liveData.value = result
                     }
                 }
                 is NetworkResponse.NetworkError->{
                     result.setMessage(response.error.message)
                 }

             }
         }
         Log.d("ProductData", liveData.value.toString())
        return  liveData
    }

    suspend fun getAllProduct( token: String): MutableLiveData<Result<ProductData>> {
        val result = Result<ProductData>()
        val data = MutableLiveData<Result<ProductData>>()
        val params= HashMap<String, Any>()

        params["page"] = 1
        params["limit"] = 10
        CoroutineScope(Dispatchers.IO).launch {
            when(val response = endPoint.fetchProduct(token,params)){
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
                is  NetworkResponse.ApiError->Log.d("ProductRepos","AppiError......")
                else->Log.d("ProductRepos","Unknown Error.....")
            }
        }


        return data
    }


}