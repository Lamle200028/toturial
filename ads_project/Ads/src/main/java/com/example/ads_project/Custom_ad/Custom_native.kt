package com.example.ads_project.Custom_ad

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.ads_project.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView

class Custom_native {
    val token_config : String = "ca-app-pub-3940256099942544/2247696110"

    fun custom_ad_native(context: Context, view: ViewGroup){
        val adLoader = AdLoader.Builder(context, token_config)
            .forNativeAd { ad : NativeAd ->
                // Show the ad.
                displayNativeAd(view, ad)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Handle the failure by logging, altering the UI, and so on.
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                // Methods in the NativeAdOptions.Builder class can be
                // used here to specify individual options settings.
                .build())
            .build()
        adLoader.loadAd(AdRequest.Builder().build())
    }
    fun displayNativeAd(parent: ViewGroup, ad: NativeAd) {

        // Inflate a layout and add it to the parent ViewGroup.
        val inflater = parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater
        val adView = inflater.inflate(R.layout.native_layout, parent) as NativeAdView

//        val headlineView = adView.findViewById<TextView>(R.id.ad_headline)
//        headlineView.text = ad.headline
//        adView.headlineView = headlineView
//
//        val mediaView = adView.findViewById<MediaView>(R.id.ad_media)
//        adView.mediaView = mediaView

        val mediaView = adView.findViewById<MediaView>(R.id.image_native)
        adView.mediaView = mediaView

        adView.setNativeAd(ad)

        parent.removeAllViews()

        parent.addView(adView)
    }
}