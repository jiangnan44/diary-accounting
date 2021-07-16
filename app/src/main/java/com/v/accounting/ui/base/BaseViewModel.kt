package com.v.accounting.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Author:v
 * Time:2021/5/20
 */
abstract class BaseViewModel : ObservableVewModel() {

    inline fun <T> launchWithViewModelScope(crossinline block: suspend () -> LiveData<T>): LiveData<T> {
        return liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            emitSource(block())
        }
    }

    inline fun launchOnViewModelScope(crossinline block: suspend () -> Unit) {
        viewModelScope.launch {
            block()
        }
    }

}
