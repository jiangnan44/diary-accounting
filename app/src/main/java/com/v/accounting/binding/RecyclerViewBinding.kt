package com.v.accounting.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Author:v
 * Time:2021/6/11
 */
object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapter")
    fun RecyclerView.bindAdapter(baseAdapter: RecyclerView.Adapter<*>) {
        adapter = baseAdapter
        layoutManager = LinearLayoutManager(context).also { llm ->
            llm.orientation = LinearLayoutManager.VERTICAL
        }
    }

    @JvmStatic
    @BindingAdapter("adapter", "isVertical", "itemDecoration", requireAll = true)
    fun RecyclerView.bindAdapterWithDecor(
        baseAdapter: RecyclerView.Adapter<*>,
        isVertical: Boolean = true, decor: RecyclerView.ItemDecoration?
    ) {
        adapter = baseAdapter
        layoutManager = LinearLayoutManager(context).also { llm ->
            llm.orientation =
                if (isVertical) LinearLayoutManager.VERTICAL else LinearLayoutManager.HORIZONTAL
        }
        decor?.let {
            addItemDecoration(it)
        }
    }
}