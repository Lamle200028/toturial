package com.example.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.navigation.databinding.ActivitySplashBinding

class splash : AppCompatActivity() {

    lateinit var activitySplashBinding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding.root)

        activitySplashBinding.apply {
            chuyenCungActivity.setOnClickListener(){
                startActivity(Intent(this@splash, MainActivity::class.java))
                finish()
            }
        }

    }
}