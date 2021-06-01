package com.v.accounting.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KClass

/**
 * Author:v
 * Time:2021/5/20
 */
abstract class BaseVMActivity : BaseActivity() {

    override fun initActivity() {
        initView()
        initToolbar()
        initVM()
    }

    abstract fun initView()
    abstract fun initVM()

    fun <T : ViewDataBinding> setContentViewWithBinding(@LayoutRes id: Int, clazz: KClass<T>): T {
        return (DataBindingUtil.setContentView(this, id) as T).apply {
            lifecycleOwner = this@BaseVMActivity
        }
    }
}