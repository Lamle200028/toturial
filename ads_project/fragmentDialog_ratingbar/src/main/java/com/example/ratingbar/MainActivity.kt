package com.example.ratingbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.ratingbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.apply {
            ratingapp.setOnClickListener(){
                var ratingDialog : Rating_dialog = Rating_dialog()
                ratingDialog.show(supportFragmentManager, null)

            }
        }

    }
}