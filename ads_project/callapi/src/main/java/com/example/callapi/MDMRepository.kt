package com.example.callapi

import android.database.Observable
import com.example.callapi.model.LoginPost
import com.example.callapi.model.LoginResponse
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class MDMRepository @Inject constructor(private val mdmApi: ApiService,) {
    suspend fun login(post: LoginPost): Response<LoginResponse> {
        return mdmApi.getUser(post)
    }

}