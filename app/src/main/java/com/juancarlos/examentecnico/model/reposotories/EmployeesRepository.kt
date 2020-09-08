package com.juancarlos.examentecnico.model.reposotories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.juancarlos.examentecnico.model.entities.GeneralResponse
import com.juancarlos.examentecnico.model.services.ApiAdapter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeesRepository {
    var urlEmployeesJson: MutableLiveData<String> = MutableLiveData()
    fun callEmployees(){

        ApiAdapter.apiService.getEmployees().enqueue(object : Callback<GeneralResponse>{
            override fun onResponse(
                call: Call<GeneralResponse>,
                response: Response<GeneralResponse>
            ) {
                if(response.isSuccessful && response.body()?.success == true ){
                    val urlString = response.body()?.data?.get("file") as String
                    urlEmployeesJson.postValue(urlString)
                }
            }

            override fun onFailure(call: Call<GeneralResponse>, t: Throwable) {
                Log.e("","")
            }

        })
    }
}