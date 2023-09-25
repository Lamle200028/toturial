package com.example.callapi

import android.app.usage.UsageEvents
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.callapi.model.LoginPost
import com.example.callapi.model.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainactivityViewModel @Inject constructor(
    private val mdmRepository: MDMRepository, @ApplicationContext private val context: Context
) : ViewModel() {
    private val _loginResponseLiveData: MutableLiveData<LoginResponse> = MutableLiveData()
    val loginResponseLiveData: LiveData<LoginResponse>
        get() = _loginResponseLiveData

    fun login(post: LoginPost) = viewModelScope.launch() {
        val response = mdmRepository.login(post)
        _loginResponseLiveData.value = response.body()

    }
}