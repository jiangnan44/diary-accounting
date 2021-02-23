package com.v.bindtest.observable

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.v.bindtest.R
import com.v.bindtest.databinding.ItemAdBinding
import com.v.bindtest.databinding.ItemGoodsBinding

/**
 * Author:v
 * Time:2021/2/19
 */
class GoodsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private val mList: MutableList<IBindingBean>
    private val mContext: Context
    private var adClickListener: View.OnClickListener? = null


    constructor(context: Context, list: MutableList<IBindingBean>) {
        mContext = context
        mList = list
    }

    fun setAdItemClickListener(listener: View.OnClickListener) {
        adClickListener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return mList[position].getViewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        return when (viewType) {
            R.layout.item_goods -> {
                val binding: ItemGoodsBinding =
                    DataBindingUtil.inflate(inflater, viewType, parent, false)
                GoodsHolder(binding)
            }
            R.layout.item_ad -> {
                val binding: ItemAdBinding =
                    DataBindingUtil.inflate(inflater, viewType, parent, false)
                AdHolder(binding)
            }
            else -> {
                val binding: ItemGoodsBinding =
                    DataBindingUtil.inflate(inflater, viewType, parent, false)
                GoodsHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GoodsHolder) {
            val goods = mList[position] as GoodsBean
            holder.binding.goods = goods
            holder.binding.executePendingBindings()
            holder.itemView.setOnClickListener {
                Toast.makeText(mContext, "goods click:$position", Toast.LENGTH_SHORT).show()
            }
        } else if (holder is AdHolder) {
            val ad = mList[position] as AdBean
            holder.binding.ad = ad
            holder.binding.itemClickListener = adClickListener
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    class AdHolder : RecyclerView.ViewHolder {
        val binding: ItemAdBinding

        constructor(binding: ItemAdBinding) : super(binding.root) {
            this.binding = binding
        }
    }

    class GoodsHolder : RecyclerView.ViewHolder {
        val binding: ItemGoodsBinding

        constructor(binding: ItemGoodsBinding) : super(binding.root) {
            this.binding = binding
        }
    }
}