package com.example.phonepe.helpers.networkHelper

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    private val BASE_URL = "https://api.themoviedb.org"


    companion object{
        @JvmStatic
        val API_KEY = "38a73d59546aa378980a88b645f487fc"
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun getApiService(): ApiInterface{
        return retrofit.create()
    }
}