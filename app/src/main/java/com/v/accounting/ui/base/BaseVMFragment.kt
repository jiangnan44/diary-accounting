package com.v.accounting.ui.base

import androidx.databinding.ViewDataBinding

/**
 * Author:v
 * Time:2021/6/2
 */
abstract class BaseVMFragment<B : ViewDataBinding, VM : BaseViewModel> : BaseBindingFragment<B>() {

    abstract fun setupViewModel(vm: VM)
}