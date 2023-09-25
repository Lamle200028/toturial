package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import com.example.myapplication.databinding.CustomSeekbarLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.math.round

class BottomSheetDialog : BottomSheetDialogFragment() {
    var customSeekbarLayoutBinding : CustomSeekbarLayoutBinding? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var customSeekbarLayoutBinding : CustomSeekbarLayoutBinding = CustomSeekbarLayoutBinding.inflate(inflater, container, false)
        customSeekbarLayoutBinding.apply {
            setSeekBar(anhsang,anhsangview, 50)
            setSeekBar(amthanh, amthanhview,10)
        }
        return customSeekbarLayoutBinding.root
    }
    fun setSeekBar(view: SeekBar, textView: TextView, seekbarmax : Int){
        view.max = seekbarmax
        view.progress = 0

        view.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                textView.text = progress.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
        textView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Cập nhật giá trị của SeekBar khi người dùng nhập số vào TextView
                val progress = (s?.toString()?.toDoubleOrNull() ?: 0.0)
                val pro = round(progress)
                view.progress = pro.toInt()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}