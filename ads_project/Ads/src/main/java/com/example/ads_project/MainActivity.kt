package com.example.ads_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.ads_project.Custom_ad.Custom_banner
import com.example.ads_project.Custom_ad.Custom_interstitial
import com.example.ads_project.Custom_ad.Custom_native
import com.example.ads_project.Custom_ad.Custom_reward
import com.example.ads_project.databinding.ActivityMainBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAdView

class MainActivity : AppCompatActivity() {
    var mainconfig : Mainconfig = Mainconfig()
    lateinit var activityMainBinding: ActivityMainBinding
    private var mInterstitialAd: InterstitialAd? = null
    lateinit var button : Button
    lateinit var button_reward : Button
    lateinit var mAdView : AdView
    lateinit var mNaView : NativeAdView
    var custom_banner : Custom_banner = Custom_banner()
    var customInterstitial : Custom_interstitial = Custom_interstitial()
    var customNative : Custom_native = Custom_native()
    var customReward : Custom_reward = Custom_reward()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            // ad_banner
            mAdView = findViewById(R.id.adview)
            custom_banner.custom_ad_banner(this@MainActivity, mAdView)

            // ad_inter
            button = findViewById(R.id.button_test)
            customInterstitial.custom_inter(this@MainActivity, button)

            // ad_native
            mNaView = findViewById(R.id.ctr_native)
//        customNative.custom_ad_native(this,mNaView)

            // ad reward
            button_reward = findViewById(R.id.button_test_reward)
            customReward.custom_ad_reward(this@MainActivity, button_reward)

    }
}