package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    var bottomSheetDialog: BottomSheetDialog = BottomSheetDialog()
    lateinit var activityMainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.apply {

            clickSeekbar.setOnClickListener(){
                bottomSheetDialog.show(supportFragmentManager, "seekbar")
            }
        }
    }

}