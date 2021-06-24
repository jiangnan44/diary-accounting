package com.v.accounting.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

object ToastManager {

    fun showLong(context: Context?, text: String?) {
        Toast.makeText(context?.applicationContext, text, Toast.LENGTH_LONG).show()
    }

    fun showLong(context: Context?, textID: Int) {
        Toast.makeText(context?.applicationContext, textID, Toast.LENGTH_LONG).show()
    }

    fun showShort(context: Context?, text: String?) {
        Toast.makeText(context?.applicationContext, text, Toast.LENGTH_SHORT).show()
    }

    fun showShort(context: Context?, textID: Int) {
        Toast.makeText(context?.applicationContext, textID, Toast.LENGTH_SHORT).show()
    }

}