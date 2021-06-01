package com.v.accounting.ui

import com.v.accounting.R
import com.v.accounting.databinding.ActivityMainBinding
import com.v.accounting.ui.base.BaseVMActivity
import com.v.accounting.utils.CommonUtil

class MainActivity : BaseVMActivity() {


    override fun initView() {
        val binding = setContentViewWithBinding(R.layout.activity_main, ActivityMainBinding::class)
        binding.tvHint.setOnClickListener {
            CommonUtil.go2Activity(this, AddAuthorActivity::class.java)
        }
    }

    override fun initVM() {

    }


}