package com.v.bindtest.observable

import com.v.bindtest.R

/**
 * Author:v
 * Time:2021/2/19
 */
data class AdBean(val type: Int, val title: String, val content: String) : IBindingBean {
    override fun getViewType(): Int = R.layout.item_ad
}

data class GoodsBean(val name: String, val icon: String?, val content: String) : IBindingBean {
    override fun getViewType(): Int = R.layout.item_goods
}

interface IBindingBean {
    fun getViewType(): Int
}

