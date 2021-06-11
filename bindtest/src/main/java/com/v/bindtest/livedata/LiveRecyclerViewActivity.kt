package com.v.bindtest.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.v.bindtest.R
import com.v.bindtest.databinding.ActivityLiveRecyclerViewBinding

class LiveRecyclerViewActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "LiveRecycler"
    }

    private lateinit var binding: ActivityLiveRecyclerViewBinding
    private lateinit var viewModel0: StudentViewModel
    private lateinit var adapter0: StudentAdapter


    private lateinit var viewModel1: LiveStudentViewModel
    private lateinit var adapter1: StudentLiveAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_live_recycler_view)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_live_recycler_view)
        binding.lifecycleOwner = this

//        observeWay()
        liveWay()


    }

    private fun liveWay() {
        viewModel1 = ViewModelProvider(this).get(LiveStudentViewModel::class.java)
        adapter1 = StudentLiveAdapter(viewModel1.data).also {
            lifecycle.addObserver(it)
        }


        binding.alrRv.let {
            it.adapter = adapter1
            it.layoutManager = LinearLayoutManager(this).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }


        //put those click logic in viewmodel,here i just lazy,@See app module
        binding.clickListener = View.OnClickListener {
            when (it!!.id) {
                R.id.alr_btn_add -> {
                    val r = (Math.random() * 10).toInt()
                    Log.d(TAG, "add data R=$r")
                    viewModel1.addData(StudentBean(100 + r, "LockLock$r", 10 + r, null))
                }
                R.id.alr_btn_refresh -> {
                    viewModel1.loadData()
                }
                R.id.alr_btn_remove -> {
                    val student = viewModel1.data[0]
                    viewModel1.removeData(student)
                }
                R.id.alr_btn_remove_all -> {
                    viewModel1.clearData()
                }
            }
        }
    }


    override fun onStop() {
        super.onStop()
        viewModel1.loadDataOnStop()
    }


    private fun observeWay() {
        viewModel0 = ViewModelProvider(this).get(StudentViewModel::class.java)
        adapter0 = StudentAdapter()

        binding.alrRv.let {
            it.adapter = adapter0
            it.layoutManager = LinearLayoutManager(this).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        //this is not elegant compare to observableList,cause there are add,remove,remove-range
        viewModel0.data.observe(this, object : Observer<List<StudentBean>> {
            override fun onChanged(list: List<StudentBean>?) {
                Log.d(TAG, "onChange size=${list?.size}")
                list?.let {
                    adapter0.updateDatas(it)
                }
            }
        })

        //put those click logic in viewmodel,here i just lazy
        binding.clickListener = View.OnClickListener {
            when (it!!.id) {
                R.id.alr_btn_add -> {
                    val r = (Math.random() * 10).toInt()
                    viewModel0.addData(StudentBean(100 + r, "LockLock$r", 10 + r, null))
                }
                R.id.alr_btn_refresh -> {
                    viewModel0.loadData()
                }
                R.id.alr_btn_remove -> {
                    val student = viewModel0.data.value!![0]
                    viewModel0.removeData(student)
                }
                R.id.alr_btn_remove_all -> {
                    viewModel0.clearData()
                }
            }
        }
    }


}