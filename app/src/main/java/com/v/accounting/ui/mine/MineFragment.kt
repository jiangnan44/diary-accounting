package com.v.accounting.ui.mine

import android.util.Log
import android.view.View
import com.v.accounting.R
import com.v.accounting.databinding.FragmentMineBinding
import com.v.accounting.ui.base.BaseBindingFragment
import com.v.accounting.ui.vm.UserViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * Author:v
 * Time:2021/6/2
 */
class MineFragment : BaseBindingFragment<FragmentMineBinding>() {

    override fun getRootViewId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(root: View) {
        bindingViewModel {
            vm = getViewModel()
            adapter = MineAdapter()
        }
    }




}