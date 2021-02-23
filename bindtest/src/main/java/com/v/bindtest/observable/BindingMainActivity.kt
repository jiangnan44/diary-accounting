package com.v.bindtest.observable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.v.bindtest.R
import com.v.bindtest.databinding.ActivityMainBinding
import com.v.bindtest.livedata.LiveMainActivity

class BindingMainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val user = UserBean("Jack", 21, "XiHu")
        binding.user = user
        binding.area = "Guangzhou"
        binding.array = arrayOf("1", "2")
        binding.list = listOf("a", "b", "c")
        binding.map = mapOf(Pair("k0", "v0"), Pair("k1", "v1"))

        binding.clickListener = this
        binding.clickHandler = OnClickHandler()
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.am_btn_observable) {
            startActivity(Intent(this, LiveMainActivity::class.java))
            return
        }

        startActivity(Intent(this, BindingRecyclerViewActivity::class.java))
        Toast.makeText(this, "haha", Toast.LENGTH_SHORT).show()
    }
}

class OnClickHandler {
    fun onClickMark(view: View) {
        view.context.startActivity(Intent(view.context, BindingObservableActivity::class.java))
        Toast.makeText(view.context, "you click me", Toast.LENGTH_SHORT).show()
    }
}