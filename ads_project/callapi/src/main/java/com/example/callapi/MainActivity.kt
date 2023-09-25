package com.example.callapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.callapi.model.LoginPost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainactivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var loginPost : LoginPost = LoginPost("ibme.lab@gmail.com", "12345689bvka")
    }
}