package com.example.ads_project.Custom_ad

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import com.example.ads_project.Home
import com.example.ads_project.Mainconfig
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class Custom_interstitial {
    var mainconfig : Mainconfig = Mainconfig()
    private var mInterstitialAd: InterstitialAd? = null

    fun custom_inter(context: Context, view : View){
        view.setOnClickListener(){
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(context as Activity)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
        }

        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError?.toString()?.let { Log.d(mainconfig.TAG1, it) }
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(mainconfig.TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                        Log.d(mainconfig.TAG, "Ad was clicked.")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        Log.d(mainconfig.TAG, "Ad dismissed fullscreen content.")
                        mInterstitialAd = null
                        startmain(context)
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                        // Called when ad fails to show.
                        Log.e(mainconfig.TAG, "Ad failed to show fullscreen content.")
                        mInterstitialAd = null
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        Log.d(mainconfig.TAG, "Ad recorded an impression.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(mainconfig.TAG, "Ad showed fullscreen content.")
                    }
                }
            }
        })
    }
    fun startmain(context: Context){
        var intent : Intent = Intent(context, Home::class.java)
        context.startActivity(intent)
    }
}