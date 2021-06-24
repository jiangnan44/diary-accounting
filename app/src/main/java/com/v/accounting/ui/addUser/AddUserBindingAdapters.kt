package com.v.accounting.ui.addUser

import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Author:v
 * Time:2021/6/15
 */
object AddUserBindingAdapters {


    @JvmStatic
    @BindingAdapter("textChangeListener")
    fun addTextChangeListener(et: EditText, liveText: MutableLiveData<String>) {
        et.addTextChangedListener { txt ->
            val t = txt?.toString()
            if (t != liveText.value.toString()) {
                liveText.value = t
            }
        }
    }


    @JvmStatic
    @BindingAdapter("checkedChangeListener")
    fun addRadioChangeListener(rg: RadioGroup, liveData: MutableLiveData<Byte>) {
        rg.setOnCheckedChangeListener { _, checkedId ->
            for (i in 0 until rg.childCount) {
                if (rg.getChildAt(i).id == checkedId) {
                    liveData.value = i.toByte()
                }
            }
        }
    }

}