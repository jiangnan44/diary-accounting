package com.v.accounting.ui.mine

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.v.accounting.R
import com.v.accounting.databinding.FragmentMineBinding
import com.v.accounting.ui.base.BaseVMFragment
import com.v.accounting.utils.ToastManager
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Author:v
 * Time:2021/6/2
 */
class MineFragment : BaseVMFragment<FragmentMineBinding>() {
    private val mineViewModel: MineViewModel by viewModel()


    override fun getRootViewId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(root: View) {
        binding.rv.run {
            adapter = MineAdapter()
            layoutManager = LinearLayoutManager(context).also { llm ->
                llm.orientation = LinearLayoutManager.VERTICAL
            }
        }

        binding.ivAvatar.setOnClickListener(clickListener)
        binding.vName.setOnClickListener(clickListener)

    }


    private var clickListener: View.OnClickListener? = View.OnClickListener { v ->
        when (v) {
            binding.ivAvatar,
            binding.vName -> {
                ToastManager.showShort(v.context, "click user")
            }
        }
    }


    override fun onDestroy() {
        clickListener = null
        super.onDestroy()
    }
}