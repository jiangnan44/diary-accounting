package com.v.bindtest.livedata

import android.content.Intent
import android.graphics.Color
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.v.bindtest.R
import com.v.bindtest.databinding.ActivityLiveMainBinding

class LiveMainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "LiveData"


    private lateinit var binding: ActivityLiveMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w(TAG,"onCreate")
//        setContentView(R.layout.activity_live_main)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_live_main
        )

        val vm = ViewModelProvider(this).get(CupViewModel::class.java)

        binding.viewmodel = vm
        binding.lifecycleOwner = this

        binding.clickListener = this

        NetworkLiveData.getInstance(this)!!.observe(this,
            { t -> Log.d(TAG, "onChanged:networkInfo=$t") })

        lifecycle.addObserver(StudentPresenter())
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.alm_btn0,
            R.id.alm_btn1,
            R.id.alm_btn2 -> {
                (v as Button).run {
                    val color = Color.parseColor(this.text.toString())
                    binding.viewmodel?.onChangeColor(color)
                    binding.viewmodel?.name!!.value = "I am a good cup"
                }
            }
            R.id.alm_tv_name -> {
                startActivity(Intent(this, LiveRecyclerViewActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.w(TAG, "onResume")
    }


    override fun onPause() {
        super.onPause()
        Log.w(TAG, "onPause")
    }

    override fun onStart() {
        super.onStart()
        Log.w(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.w(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.w(TAG, "onDestroy")
    }
}