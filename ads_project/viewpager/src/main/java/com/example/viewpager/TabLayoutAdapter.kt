package com.example.customtablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabLayoutAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                InspectionFragment()
            }
            1 -> {
                AuctionFragment()
            }
            2 -> {
                MarketPlaceFragment()
            }
            3 -> {
                MarketPlaceFragment()
            }
            4 -> {
                MarketPlaceFragment()
            }
            else -> {
                InspectionFragment()
            }
        }
    }
}