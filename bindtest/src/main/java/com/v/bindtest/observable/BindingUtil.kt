package com.v.bindtest.observable

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.v.bindtest.R

/**
 * Author:v
 * Time:2021/2/19
 */
object  BindingUtil {

    @BindingAdapter("setIcon")
    @JvmStatic
    fun setIcon(iv: ImageView, url: String?) {
        if (url == null) {
            iv.setImageResource(R.mipmap.ic_launcher)
        } else {
//            loadImage(url)
        }
    }


}