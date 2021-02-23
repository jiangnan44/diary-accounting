package com.v.bindtest.livedata

import android.text.TextUtils
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.v.bindtest.R

/**
 * Author:v
 * Time:2021/2/20
 */
object BindingAdapter {

    @BindingAdapter("app:loadIcon")
    @JvmStatic
    fun loadIcon(iv: ImageView, url: String?) {
        if (TextUtils.isEmpty(url)) {
            iv.setImageResource(R.mipmap.ic_launcher)
        } else {
            //load url
        }
    }
}