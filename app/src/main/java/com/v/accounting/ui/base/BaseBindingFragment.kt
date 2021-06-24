package com.v.accounting.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.v.accounting.utils.ToastManager
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

/**
 * Author:v
 * Time:2021/6/2
 */
abstract class BaseBindingFragment<B : ViewDataBinding> : BaseFragment() {
    protected var binding: B? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            (DataBindingUtil.inflate(inflater, getRootViewId(), container, false) as B).apply {
                lifecycleOwner = viewLifecycleOwner
            }
        return binding!!.root
    }




    protected inline fun bindingViewModel(block: B.() -> Unit) {
        binding?.apply(block)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}