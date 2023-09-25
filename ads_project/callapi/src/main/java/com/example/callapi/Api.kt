package com.example.callapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Api {
    var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://bvdemo.qltbyt.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service: ApiService = retrofit.create(ApiService::class.java)
}