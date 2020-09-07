package com.juancarlos.examentecnico.model.services

import com.juancarlos.examentecnico.model.entities.GeneralResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("getFile.json?dl=0")
    fun getEmployees(): Call<GeneralResponse>
}