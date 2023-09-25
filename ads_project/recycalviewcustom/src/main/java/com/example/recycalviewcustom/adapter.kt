package com.example.recycalviewcustom

import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class adapter(private var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<adapter.ViewHolder>() {
    private val listItem = arrayListOf<item>()
    inner class ViewHolder(): RecyclerView.ViewHolder() {
        var im_item: ImageView? = null
        var tv_name: TextView? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listItem.size
    }
}