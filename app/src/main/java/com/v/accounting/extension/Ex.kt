package com.v.accounting.extension

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlin.reflect.KClass

/**
 * Author:v
 * Time:2021/6/1
 */
fun <T : Activity> Activity.go2Activity(clazz: KClass<T>) {
    startActivity(Intent(this, clazz.java))
}

@Suppress("DEPRECATION")
fun Activity.hideSystemBar(view: View) {
    val controller = ViewCompat.getWindowInsetsController(view)
    if (controller == null) {
        with(window) {
            val flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            decorView.systemUiVisibility = flag
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }
    } else controller.let {
        it.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        it.hide(WindowInsetsCompat.Type.systemBars())
    }
}