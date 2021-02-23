package com.v.bindtest.livedata

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Author:v
 * Time:2021/2/20
 */
class CupViewModel : ViewModel() {
    private val _color = MutableLiveData(Color.GREEN)


    val name= MutableLiveData("Cup")
    val color: LiveData<Int> = _color


    fun onChangeColor(color: Int) {
        _color.value = color
        Log.d("vvv", "onchange--$color")
    }
}