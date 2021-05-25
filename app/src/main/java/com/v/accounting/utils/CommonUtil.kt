package com.v.accounting.utils

import android.app.Activity
import android.content.Intent

/**
 * Author:v
 * Time:2021/5/20
 */
object CommonUtil {
    fun <T : Activity> go2Activity(activity: Activity, clazz: Class<T>) {
        activity.startActivity(Intent(activity, clazz))
    }

}