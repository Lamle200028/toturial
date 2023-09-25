package com.example.coroutine

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Funtion {
    var listUser : List<User> = listOf()
    fun getdata(){
        GlobalScope.launch(Dispatchers.IO) {
            Log.d("mainTest", "abc")
            listUser = RetrofitCustom.service.getData().body()!!
            withContext(Dispatchers.Main){
                Log.d("mainTest", "abc1")
            }
        }
    }
}