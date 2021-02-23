package com.v.bindtest.livedata

import android.util.Log
import androidx.lifecycle.*

/**
 * Author:v
 * Time:2021/2/23
 */
class StudentPresenter : LifecycleEventObserver {
    private val TAG = "StudentPresenter"


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> onCreate(source)
            Lifecycle.Event.ON_START -> onStart(source)
            Lifecycle.Event.ON_RESUME -> onResume(source)
            Lifecycle.Event.ON_PAUSE -> onPause(source)
            Lifecycle.Event.ON_STOP -> onStop(source)
            Lifecycle.Event.ON_DESTROY -> onDestroy(source)
            Lifecycle.Event.ON_ANY -> onAllEvent(source)
        }
    }


    fun onCreate(owner: LifecycleOwner?) {
        Log.d(TAG, "onCreate..")
    }

    fun onStart(owner: LifecycleOwner?) {
        Log.d(TAG, "onStart..")
    }

    fun onResume(owner: LifecycleOwner?) {
        Log.d(TAG, "onResume..")
    }

    fun onPause(owner: LifecycleOwner?) {
        Log.d(TAG, "onPause..")
    }

    fun onStop(owner: LifecycleOwner?) {
        Log.d(TAG, "onStop..")
    }

    fun onDestroy(owner: LifecycleOwner?) {
        Log.d(TAG, "onDestroy..")
    }

    fun onAllEvent(owner: LifecycleOwner?) {
        Log.d(TAG, "onAllEvent--${owner?.lifecycle?.currentState}")
    }
}