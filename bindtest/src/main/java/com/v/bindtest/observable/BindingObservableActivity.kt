package com.v.bindtest.observable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import com.v.bindtest.R
import com.v.bindtest.databinding.ActivityMain3Binding
import kotlin.math.roundToInt

class BindingObservableActivity : AppCompatActivity(), View.OnClickListener {
    private val list = ObservableArrayList<String>()

    private var bean: NewBean? = null
    private var bea: OldBean? = null
    private var flag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main3)

        val binding =
            DataBindingUtil.setContentView<ActivityMain3Binding>(this, R.layout.activity_main3)

        bean = NewBean().apply {
            content = "I am the old content"
            title = "I am old title"
        }

        bea = OldBean().apply {
            light.set("I am old Light")
            window.set((Math.random() * 10f).roundToInt())
        }

        list.add("item0")
        list.add("item1")

        binding.newbee = bean
        binding.oldbea = bea
        binding.list = list
    }

    override fun onClick(v: View?) {
        if (flag) {
            bean?.content = "I am new Content"
            bean?.title = "I am new title"
            bea?.light?.set("I am new Light")
            bea?.window!!.set((Math.random() * 10f).roundToInt())
        } else {
            bean?.content = "I am old Content"
            bean?.title = "I am old title"
            bea?.light?.set("I am old Light")
            bea?.window!!.set((Math.random() * 10f).roundToInt())
        }
        flag = !flag
    }
}