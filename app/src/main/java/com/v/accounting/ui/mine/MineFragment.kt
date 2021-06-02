package com.v.accounting.ui.mine

import android.view.View
import com.v.accounting.R
import com.v.accounting.databinding.FragmentMineBinding
import com.v.accounting.ui.base.BaseVMFragment
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
        binding.tvMine.text = "mine fragment"
    }
}