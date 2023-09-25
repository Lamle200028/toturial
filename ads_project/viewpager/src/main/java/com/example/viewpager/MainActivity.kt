package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.customtablayout.TabLayoutAdapter
import com.example.viewpager.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
//        addMarginToTabItem()
        setUpViewPagerAdapter()


    }

    private fun addMarginToTabItem(){
        val tabs = binding.tabLayout.getChildAt(0) as ViewGroup
        for (i in 0 until tabs.childCount ) {
            val tab = tabs.getChildAt(i)
            val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
            layoutParams.weight = 0f
            layoutParams.setMargins(0,0,20,0)
//            layoutParams.rightMargin = resources.getDimensionPixelSize(20)
            tab.layoutParams = layoutParams
            binding.tabLayout.requestLayout()
        }
    }

    private fun setUpViewPagerAdapter(){
        val adapter = TabLayoutAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })

    }
}