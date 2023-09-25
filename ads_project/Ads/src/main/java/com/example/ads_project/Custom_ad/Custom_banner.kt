package com.example.ads_project.Custom_ad

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.ads_project.Home
import com.google.android.gms.ads.*

class Custom_banner {
    fun custom_ad_banner(context: Context, view: AdView){
        val adView = AdView(context)

        // setup view banner
        adView.adSize = AdSize.BANNER
        adView.adUnitId = "ca-app-pub-3940256099942544/2247696110"

        MobileAds.initialize(context) {
            val adRequest = AdRequest.Builder().build()
            view.loadAd(adRequest)
            view.adListener = object : AdListener() {
                override fun onAdClicked() {
                    startmain(context)
                    Log.d("checkk onAdClicked", "abc")
                }
                override fun onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    startmain(context)
                    Log.d("checkk adFailedToLoad", "abc")
                }
                override fun onAdImpression() {
                    // Code to be executed when an impression is recorded
                    // for an ad.
                }
                override fun onAdLoaded() {
//                    startmain(this@MainActivity)
                }
                override fun onAdOpened() {
                    startmain(context)
                }
            }
        }

    }
    fun startmain(context: Context){
        var intent : Intent = Intent(context, Home::class.java)
        context.startActivity(intent)
    }

}