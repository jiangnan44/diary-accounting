package com.v.accounting.ui.base

import androidx.lifecycle.ViewModel

/**
 * Author:v
 * Time:2021/5/20
 */
abstract class BaseViewModel : ViewModel() {
    abstract fun newInstance(): BaseViewModel
}