package com.v.accounting.ui.mine

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView

/**
 * Author:v
 * Time:2021/6/3
 */
class MineAdapter : RecyclerView.Adapter<MineAdapter.MineHolder>() {
    private val dataList = ArrayList<String>()

    init {
        for (i in 0..25) {
            dataList.add("data$i")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MineHolder {
        return MineHolder(TextView(parent.context).also {
            it.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            it.setTextColor(Color.parseColor("#666666"))
            it.setPadding(50)
        })
    }

    override fun onBindViewHolder(holder: MineHolder, position: Int) {
        (holder.itemView as TextView).run {
            text = dataList[position]
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MineHolder(view: View) : RecyclerView.ViewHolder(view)
}