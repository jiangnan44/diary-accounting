package com.v.accounting.ui

import androidx.databinding.DataBindingUtil
import com.v.accounting.R
import com.v.accounting.databinding.ActivityMainBinding
import com.v.accounting.utils.CommonUtil

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.tvHint.setOnClickListener {
            CommonUtil.go2Activity(this, AddAuthorActivity::class.java)
        }
    }

    override fun initVM() {

    }


}