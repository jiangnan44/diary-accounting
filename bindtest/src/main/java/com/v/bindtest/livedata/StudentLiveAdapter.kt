package com.v.bindtest.livedata

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import com.v.bindtest.R
import com.v.bindtest.databinding.ItemStudentsBinding
import com.v.bindtest.observable.BaseHolder

/**
 * Author:v
 * Time:2021/2/23
 */
class StudentLiveAdapter(list: ObservableArrayList<StudentBean>) :
    LiveAdapter<StudentBean, StudentLiveAdapter.StudentHolder>(list) {
    override fun isUpdateOnPause(): Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val binding = DataBindingUtil.inflate<ItemStudentsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_students,
            parent,
            false
        )

        binding.root.setOnClickListener {
            Toast.makeText(parent.context, "click=${binding.student?.name}", Toast.LENGTH_SHORT)
                .show()
        }
        return StudentHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val student = mList[position]
        holder.binding.student = student
    }


    class StudentHolder : BaseHolder {

        val binding: ItemStudentsBinding

        constructor(binding: ItemStudentsBinding) : super(binding.root) {
            this.binding = binding
        }
    }
}