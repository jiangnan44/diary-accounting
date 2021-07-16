package com.v.accounting.ui.mine

import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

/**
 * Author:v
 * Time:2021/6/15
 */
object MineBindingAdapters {


    @JvmStatic
    @BindingAdapter("textChangeListener")
    fun EditText.addTextChangeListener(liveText: MutableLiveData<String>) {
        addTextChangedListener { txt ->
            val t = txt?.toString()
            if (t != liveText.value.toString()) {
                liveText.value = t
            }
        }
    }


    @JvmStatic
    @BindingAdapter("checkedChangeListener")
    fun RadioGroup.addRadioChangeListener(liveData: MutableLiveData<Byte>) {
        setOnCheckedChangeListener { _, checkedId ->
            for (i in 0 until childCount) {
                if (getChildAt(i).id == checkedId) {
                    liveData.value = i.toByte()
                }
            }
        }
    }


}