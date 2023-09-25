package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var homeBinding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpView()
        return homeBinding.root
    }


    fun setUpView(){
        homeBinding.apply {

            //TODO cùng activity
            chuyenCungActivity.setOnClickListener(){
                  findNavController().navigate(R.id.settingFragment)
            }
            chuyenKhacActivity.setOnClickListener(){
                findNavController().navigate(R.id.splash)
            }

            name.text= arguments?.getString("name")
            name.setTextColor(R.color.white)
        }
    }

}