package com.v.bindtest.observable

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.v.bindtest.R
import com.v.bindtest.databinding.ActivityMain2Binding

class BindingRecyclerViewActivity : AppCompatActivity() {
    private lateinit var adapter: GoodsAdapter
    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        initAdapter()
    }

    private fun initAdapter() {
        adapter = GoodsAdapter(this, produceDatas())
        adapter.setAdItemClickListener {
            Toast.makeText(it.context, "ads click", Toast.LENGTH_SHORT).show()
        }
        binding.am2Rv.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = adapter
        }
    }

    private fun produceDatas(): MutableList<IBindingBean> {
        val ret = ArrayList<IBindingBean>(15)
        for (i in 0..15) {
            if (i % 5 == 0) {
                ret.add(AdBean(i, "title$i", "女神今夜有约，就在陌东陌西，速来~"))
            } else {
                ret.add(GoodsBean("葫芦娃", null, "葫芦娃，葫芦娃，坐在门前的大桥下，游过一群鸭~鸭~"))
            }
        }

        return ret
    }


}