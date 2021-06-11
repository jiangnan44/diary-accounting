package com.v.accounting.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.v.accounting.utils.ToastManager

/**
 * Author:v
 * Time:2021/6/11
 */
object ViewBinding {

    @JvmStatic
    @BindingAdapter("toast")
    fun bindToast(view: View, msg: String?) {
        msg?.let {
            ToastManager.showShort(view.context, it)
        }
    }

}

