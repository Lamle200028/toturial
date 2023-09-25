package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.navigation.databinding.FragmentSettingBinding

class SettingFragment : Fragment(){

    lateinit var fragmentSettingFragment : FragmentSettingBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentSettingFragment = FragmentSettingBinding.inflate(inflater, container, false)

        fragmentSettingFragment.apply {
            chuyenKhacActivity.setOnClickListener(){

                //TODO() truyền dữ liệu
                val bundle = bundleOf(
                    "name" to "lam dz",
                )
                findNavController().navigate(R.id.homeFragment, bundle)
            }
            chuyenSangActivity.setOnClickListener(){
                findNavController().navigate(R.id.splash)
            }
        }

        return fragmentSettingFragment.root
    }
}