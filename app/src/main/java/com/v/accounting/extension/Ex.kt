package com.v.accounting.extension

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.WindowManager

/**
 * Author:v
 * Time:2021/6/1
 */
fun <T> Activity.go2Activity(clazz: Class<T>) {
    startActivity(Intent(this, clazz))
}

@Suppress("DEPRECATION")
fun Activity.setImmersive() {
    with(window) {
        val flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        decorView.systemUiVisibility = flag
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = Color.TRANSPARENT
    }
}