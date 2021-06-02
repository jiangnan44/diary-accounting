package com.v.accounting.ui.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.v.accounting.R

/**
 * Author:v
 * Time:2021/6/2
 */
abstract class BaseFragment : Fragment() {
    protected var mToolbar: Toolbar? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getRootViewId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        initToolbar(view)
        initData()
    }

    @LayoutRes
    abstract fun getRootViewId(): Int
    abstract fun initView(root: View)
    open fun initData() {}

    protected fun initToolbar(view: View) {
        mToolbar = view.findViewById(R.id.toolbar)
        mToolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    fun setToolbarTitle(title: String) {
        mToolbar?.title = title
    }

    fun setToolbarTitle(@StringRes titleId: Int) {
        setToolbarTitle(getString(titleId))
    }

    /**
     * @param icon null to clear the icon
     */
    fun setNavigationIcon(@Nullable icon: Drawable?) {
        mToolbar?.navigationIcon = icon
    }

    fun setNavigationClickListener(block: (View?) -> Unit) {
        mToolbar?.setNavigationOnClickListener { v -> block(v) }
    }

}