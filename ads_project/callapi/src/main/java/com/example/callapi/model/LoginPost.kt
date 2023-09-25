package com.example.callapi.model

import com.google.gson.annotations.SerializedName

data class LoginPost(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
