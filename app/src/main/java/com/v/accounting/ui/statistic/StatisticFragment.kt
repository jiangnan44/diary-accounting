package com.v.accounting.ui.statistic

import android.view.View
import com.v.accounting.R
import com.v.accounting.databinding.FragmentStatisticBinding
import com.v.accounting.ui.base.BaseVMFragment

/**
 * Author:v
 * Time:2021/6/2
 */
class StatisticFragment : BaseVMFragment<FragmentStatisticBinding>() {
    private lateinit var statisticViewModel: StatisticViewModel


    override fun getRootViewId(): Int {
        return R.layout.fragment_statistic
    }

    override fun initView(root: View) {
        binding.tvStatistic.text = "statistic fragment"
    }

}