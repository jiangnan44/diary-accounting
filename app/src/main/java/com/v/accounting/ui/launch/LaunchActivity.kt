package com.v.accounting.ui.launch

import android.util.Log
import android.widget.TextView
import com.v.accounting.BuildConfig
import com.v.accounting.R
import com.v.accounting.databinding.ActivityLaunchBinding
import com.v.accounting.extension.go2Activity
import com.v.accounting.extension.hideSystemBar
import com.v.accounting.ui.MainActivity
import com.v.accounting.ui.base.BaseBindingActivity
import com.v.accounting.utils.ToastManager

class LaunchActivity : BaseBindingActivity() {
    private var counter = if (BuildConfig.DEBUG) 1 else 3

    override fun initView() {

        val binding =
            setContentViewWithBinding(R.layout.activity_launch, ActivityLaunchBinding::class)
        binding.ivAds.setOnClickListener() {
            ToastManager.showShort(this, "click ads")
        }

        hideSystemBar(binding.ivAds)
        binding.tvSkip.let { tv ->
            val txt = "skip in $counter sec"
            tv.text = txt
            startCounter(tv)

            tv.setOnClickListener {
                go2Activity(MainActivity::class)
                finish()
            }
        }
    }

    //use handler in real project,here I just lazy
    private fun startCounter(tvSkip: TextView) {
        if (counter == 0) {
            go2Activity(MainActivity::class)
            finish()
            return
        }
        tvSkip.postDelayed({
            val text = "skip in ${--counter} sec"
            tvSkip.text = text
            startCounter(tvSkip)
        }, 1000L)
    }

    override fun initVM() {
        //get ads cover or something
    }
}