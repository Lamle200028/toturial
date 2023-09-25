package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation.databinding.FragmentSplashBinding


class splashFragment : Fragment() {

    lateinit var fragmentSplashBinding : FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        fragmentSplashBinding.apply {
            chuyenKhacActivityTuslpash.setOnClickListener(){
                findNavController().navigate(R.id.mainActivity)
            }
        }
        return fragmentSplashBinding.root
    }
}