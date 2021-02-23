package com.v.bindtest.observable

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.v.bindtest.BR

/**
 * Author:v
 * Time:2021/2/19
 */
class NewBean() : BaseObservable() {
    var content: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.content)
        }

    var title: String? = null
        set(value) {
            field = value
            notifyChange()
        }
}

class OldBean {
    val light = ObservableField<String>()
    val window = ObservableInt()
}
