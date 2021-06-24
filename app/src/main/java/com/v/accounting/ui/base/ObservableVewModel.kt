package com.v.accounting.ui.base

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import com.v.accounting.BR

/**
 * Author:v
 * Time:2021/6/23
 */
open class ObservableVewModel : ViewModel(), Observable {
    private var callbacks: PropertyChangeRegistry? = null

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        val registries = callbacks ?: PropertyChangeRegistry().also {
            callbacks = it
        }
        registries.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks?.remove(callback)
    }


    fun notifyAllPropertiesChanged() {
        callbacks?.notifyCallbacks(this, BR._all, null)
    }

    fun clearAllProperties() {
        callbacks?.clear()
    }

    fun notifyPropertyChanged(fieldId: Int) {
        callbacks?.notifyCallbacks(this, fieldId, null)
    }

    override fun onCleared() {
        super.onCleared()
        clearAllProperties()
    }
}