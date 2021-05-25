package com.v.bindtest.livedata

import android.util.Log
import androidx.annotation.NonNull
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.v.bindtest.observable.BaseHolder

/**
 * Author:v
 * Time:2021/2/23
 */
abstract class LiveAdapter<T, VH : BaseHolder> : RecyclerView.Adapter<VH>, LifecycleEventObserver {
    private val TAG = "LiveAdapter"
    protected val mList: ObservableArrayList<T>
    protected var mListChangeListener: ObservableList.OnListChangedCallback<ObservableArrayList<T>>? =
        null

    //in this way,you don't need to hold an adapter in ViewModel,in stead,hold the observable dataSource,
    constructor(@NonNull list: ObservableArrayList<T>) {
        mList = list
        addListChangedListener()
    }

    private fun addListChangedListener() {
        if (mListChangeListener == null) {
            mListChangeListener = object :
                ObservableList.OnListChangedCallback<ObservableArrayList<T>>() {
                override fun onChanged(sender: ObservableArrayList<T>?) {
                    Log.d(TAG, "onChanged")
                    notifyDataSetChanged()
                }


                override fun onItemRangeChanged(
                    sender: ObservableArrayList<T>?,
                    positionStart: Int,
                    itemCount: Int
                ) {
                    Log.d(TAG, "onItemRangeChanged")
                    notifyItemRangeChanged(positionStart, itemCount)
                }

                override fun onItemRangeInserted(
                    sender: ObservableArrayList<T>?,
                    positionStart: Int,
                    itemCount: Int
                ) {
                    Log.d(
                        TAG,
                        "onItemRangeInserted start:$positionStart ,count:$itemCount size=${sender?.size}"
                    )
                    Log.d(TAG, "onItemRangeInserted mList size=${mList.size}")
                    notifyItemRangeInserted(positionStart, itemCount)
                }

                override fun onItemRangeMoved(
                    sender: ObservableArrayList<T>?,
                    fromPosition: Int,
                    toPosition: Int,
                    itemCount: Int
                ) {
                    if (itemCount == 1) {
                        notifyItemMoved(fromPosition, toPosition)
                    } else {
                        notifyDataSetChanged()
                    }
                }

                override fun onItemRangeRemoved(
                    sender: ObservableArrayList<T>?,
                    positionStart: Int,
                    itemCount: Int
                ) {
                    Log.d(TAG, "onItemRangeRemoved")
                    notifyItemRangeRemoved(positionStart, itemCount)
                }
            }
        }
        mList.addOnListChangedCallback(mListChangeListener)
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
            }
            Lifecycle.Event.ON_START -> {
            }
            Lifecycle.Event.ON_STOP -> {
            }
            Lifecycle.Event.ON_RESUME -> onResume()
            Lifecycle.Event.ON_PAUSE -> onPause()
            Lifecycle.Event.ON_DESTROY -> onDestroy()
        }
    }


    open fun isUpdateOnPause(): Boolean = false

    private fun onPause() {
        Log.d(TAG, "onPause $mListChangeListener")
        if (!isUpdateOnPause()) {//could do this work in mListChangeListener instead of remove and add it
            Log.d(TAG, "onPause remove listener")
            mList.removeOnListChangedCallback(mListChangeListener)
        }
    }

    private fun onResume() {
        Log.d(TAG, "onResume ")
        Log.d(TAG, "onResume listener $mListChangeListener")
        if (!isUpdateOnPause()) {
            notifyDataSetChanged()//maybe check whether list changed
            addListChangedListener()
        }
    }

    //maybe something else
    open fun onDestroy() {
        mListChangeListener?.let {
            mList.removeOnListChangedCallback(mListChangeListener)
            mListChangeListener = null
        }
    }

}