package com.v.bindtest.livedata

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.v.bindtest.R
import com.v.bindtest.databinding.ItemStudentsBinding

/**
 * Author:v
 * Time:2021/2/22
 */
class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private val datas: ArrayList<StudentBean>


    constructor() {
        datas = ArrayList()
    }

    fun updateDatas(list: List<StudentBean>) {
        if (list.isNotEmpty()) {
            datas.clear()
            datas.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding: ItemStudentsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_students, parent, false
        )
        binding.root.setOnClickListener {
            Toast.makeText(parent.context, "click=${binding.student?.name}", Toast.LENGTH_SHORT)
                .show()
        }
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = datas[position]
        holder.binding.student = student
    }

    override fun getItemCount(): Int {
        return datas.size
    }


    class StudentViewHolder : RecyclerView.ViewHolder {

        val binding: ItemStudentsBinding

        constructor(binding: ItemStudentsBinding) : super(binding.root) {
            this.binding = binding
        }
    }
}