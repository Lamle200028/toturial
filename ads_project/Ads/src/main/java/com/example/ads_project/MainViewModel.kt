package com.example.ads_project

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun setclick(context: Context, view: View){
        view.setOnClickListener(){

        }
    }
}