package com.v.accounting.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KClass

/**
 * Author:v
 * Time:2021/6/2
 */
abstract class BaseVMFragment<T : ViewDataBinding> : BaseFragment() {
    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            (DataBindingUtil.inflate(inflater, getRootViewId(), container, false) as T).apply {
                lifecycleOwner = viewLifecycleOwner
            }
        return binding.root
    }


}