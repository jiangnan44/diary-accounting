package com.v.accounting.ui.launch

import android.os.Bundle
import android.widget.TextView
import com.v.accounting.R
import com.v.accounting.databinding.ActivityLaunchBinding
import com.v.accounting.extension.go2Activity
import com.v.accounting.ui.MainActivity
import com.v.accounting.ui.base.BaseVMActivity
import com.v.accounting.ui.test.Main2Activity
import com.v.accounting.utils.ToastManager

class LaunchActivity : BaseVMActivity() {
    var counter = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
    }

    override fun initView() {
        val binding =
            setContentViewWithBinding(R.layout.activity_launch, ActivityLaunchBinding::class)
        binding.ivAds.setOnClickListener {
            ToastManager.showShort(this, "click ads")
        }

        binding.tvSkip.setOnClickListener {
            go2Activity(Main2Activity::class.java)
        }

        startCounter(binding.tvSkip)
    }

    private fun startCounter(tvSkip: TextView) {
        if (counter == 0) {
            go2Activity(Main2Activity::class.java)
            return
        }
        tvSkip.postDelayed({
            tvSkip.text = "skip in ${--counter} sec"
            startCounter(tvSkip)
        }, 1000L)
    }

    override fun initVM() {

    }
}