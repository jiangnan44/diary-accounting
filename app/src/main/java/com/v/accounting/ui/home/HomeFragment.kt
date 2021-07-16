package com.v.accounting.ui.home

import android.view.View
import android.widget.Toast
import com.v.accounting.R
import com.v.accounting.databinding.FragmentHomeBinding
import com.v.accounting.ui.base.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Author:v
 * Time:2021/6/2
 */
class HomeFragment : BaseBindingFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModel()


    override fun getRootViewId(): Int {
        return R.layout.fragment_home
    }

    override fun initView(root: View) {

        binding?.tvHome?.text = "home fragment"


    }

}