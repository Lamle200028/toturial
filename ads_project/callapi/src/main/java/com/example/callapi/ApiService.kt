package com.example.callapi

import android.database.Observable
import com.example.callapi.model.LoginPost
import com.example.callapi.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("api/login")
    suspend fun getUser(
        @Body loginPost: LoginPost,
    ) : Response<LoginResponse>
}