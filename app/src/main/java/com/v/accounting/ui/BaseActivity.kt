package com.v.accounting.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Author:v
 * Time:2021/5/20
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        initView()

        initVM()
    }

    abstract fun initView()
    abstract fun initVM()

    fun <T : ViewDataBinding> setContentViewWithBinding(@LayoutRes id: Int, clazz: Class<T>): T {
        return (DataBindingUtil.setContentView(this, id) as T).apply {
            lifecycleOwner = this@BaseActivity
        }
    }
}