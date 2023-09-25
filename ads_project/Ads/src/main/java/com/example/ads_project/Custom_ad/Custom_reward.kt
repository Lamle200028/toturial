package com.example.ads_project.Custom_ad

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.View
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions

class Custom_reward {
    private var rewardedAd: RewardedAd? = null
    private final var TAG = "MainActivity"

    fun custom_ad_reward(context: Context, view: View){
        var adRequest = AdRequest.Builder().build()
        RewardedAd.load(context,"ca-app-pub-3940256099942544/5224354917", adRequest, object : RewardedAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError?.toString()?.let { Log.d(TAG, it) }
                rewardedAd = null
            }

            override fun onAdLoaded(ad: RewardedAd) {
                Log.d(TAG, "Ad was loaded.")
                rewardedAd = ad
                rewardedAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                        Log.d(TAG, "Ad was clicked.")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        // Set the ad reference to null so you don't show the ad a second time.
                        Log.d(TAG, "Ad dismissed fullscreen content.")
                        rewardedAd = null
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                        // Called when ad fails to show.
                        Log.e(TAG, "Ad failed to show fullscreen content.")
                        rewardedAd = null
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        Log.d(TAG, "Ad recorded an impression.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(TAG, "Ad showed fullscreen content.")
                    }
                }
            }

        })
        view.setOnClickListener(){
            rewardedAd?.let { ad ->
                ad.show(context as Activity, OnUserEarnedRewardListener { rewardItem ->
                    // Handle the reward.
                    val rewardAmount = rewardItem.amount
                    val rewardType = rewardItem.type
                    Log.d(TAG, "User earned the reward.")
                })
            } ?: run {
                Log.d(TAG, "The rewarded ad wasn't ready yet.")
            }
        }
    }
}