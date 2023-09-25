package com.example.coroutine

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RetrofitCustom {
    var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://bvdemo.qltbyt.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service: ApiService = retrofit.create(ApiService::class.java)
}

interface ApiService{
    @GET("abc")
    suspend fun getData() : Response<List<User>>
}

data class User(
    var id : Int =0,
    var name : String = ""
)
