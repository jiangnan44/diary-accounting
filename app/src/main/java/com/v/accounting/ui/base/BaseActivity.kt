package com.v.accounting.ui.base

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.v.accounting.R
import com.v.accounting.views.LoadingDialog

/**
 * Author:v
 * Time:2020/11/25
 */
abstract class BaseActivity : AppCompatActivity() {

    private var loadingDialog: LoadingDialog? = null
    protected var mToolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
    }

    open fun initActivity(){
        setContentView(getContentLayoutId())
        initView()
        initToolbar()
        initData()
    }

    protected fun initToolbar() {
        mToolbar = findViewById(R.id.toolbar)
        mToolbar?.setNavigationOnClickListener {
            finish()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        loadingDialog?.let {
            if (it.isShowing) it.dismiss()
            loadingDialog = null
        }
    }

    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog?.show()
    }

    fun dismissLoading() {
        loadingDialog?.let {
            if (it.isShowing) it.dismiss()
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

    fun setNavigationClickListener(listener: View.OnClickListener) {
        mToolbar?.setNavigationOnClickListener(listener)
    }

    abstract fun initView()
    open fun getContentLayoutId(): Int = 0
    open fun initData() {}
}