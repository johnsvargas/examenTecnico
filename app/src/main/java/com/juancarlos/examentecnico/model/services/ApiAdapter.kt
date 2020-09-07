package com.juancarlos.examentecnico.model.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiAdapter {
    private var API_SERVICE: ApiServices? = null

    val baseURL:String = "https://dl.dropboxusercontent.com/s/5u21281sca8gj94/"

    val apiService: ApiServices
        get() {

            //val BaseURL = Constants.BASE_URL_DJANGO
            if (API_SERVICE == null) {

                val interceptorDebug = HttpLoggingInterceptor()
                interceptorDebug.level = HttpLoggingInterceptor.Level.BODY

                val client = OkHttpClient.Builder()
                    .addInterceptor(interceptorDebug)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                API_SERVICE = retrofit.create(ApiServices::class.java)
            }
            return API_SERVICE!!
        }
}