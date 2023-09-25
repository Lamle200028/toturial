package com.example.ratingbar

import android.app.ActionBar
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Insets
import android.graphics.Point
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.ratingbar.databinding.RatingDialogBinding

class Rating_dialog : DialogFragment() {

    lateinit var binding : RatingDialogBinding
    val LINK_STORE =
        "https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}&hl=en_IE"
    val store_uri = "market://details?id=${BuildConfig.APPLICATION_ID}"
    val request_code_share = 9999


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = RatingDialogBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            closeRatingDialog.setOnClickListener(){
                requireActivity().finish()
            }
            ratingbar.setOnRatingBarChangeListener { ratingBar, rating , fromUser ->
                // Xử lý sự kiện thay đổi đánh giá của người dùng
//                super.onResume()
                when (rating){
                    1F ->{
                        imageRate.setImageResource(R.drawable.onestar)
                    }
                    2F ->{
                        imageRate.setImageResource(R.drawable.twostar)
                    }
                    3F->{
                        imageRate.setImageResource(R.drawable.threestar)
                    }
                    4F->{
                        imageRate.setImageResource(R.drawable.fourstar)
                    }
                    5F->{
                        imageRate.setImageResource(R.drawable.fivestart)
                    }
                }
            }
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        manager.beginTransaction().add(this, tag).commitAllowingStateLoss()
    }
//    private fun openAppInPlayStore() {
//        AppOpenManager.getInstance().disableAppResume()
//        val uri = Uri.parse(store_uri)
//        val goToMarketIntent = Intent(Intent.ACTION_VIEW, uri)
//
//        var flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
//        flags = flags or Intent.FLAG_ACTIVITY_NEW_DOCUMENT
//        goToMarketIntent.addFlags(flags)
//
//        try {
//            startActivityForResult(goToMarketIntent, request_code_share)
//        } catch (e: ActivityNotFoundException) {
//            val intent = Intent(
//                Intent.ACTION_VIEW, Uri.parse(LINK_STORE)
//            )
//            startActivityForResult(intent, request_code_share)
//        }
//    }
//    override fun onResume() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//            val windowMetrics = requireActivity().windowManager.currentWindowMetrics
//            val insets: Insets =
//                windowMetrics.windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
//            val width = windowMetrics.bounds.width() - insets.left - insets.right
//            val window = dialog!!.window
//            if (window != null) {
//                window.setLayout((width * 0.75).toInt(), ActionBar.LayoutParams.WRAP_CONTENT)
//                window.setGravity(Gravity.CENTER)
//            }
//            super.onResume()
//        } else {
//            val window = dialog!!.window
//            val size = Point()
//            val display = window!!.windowManager.defaultDisplay
//            display.getSize(size)
//            window.setLayout(
//                (size.x * 0.75).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT
//            )
//            window.setGravity(Gravity.CENTER)
//            super.onResume()
//        }
//    }

}