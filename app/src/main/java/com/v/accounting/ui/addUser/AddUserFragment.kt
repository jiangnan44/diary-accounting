package com.v.accounting.ui.addUser

import android.view.View
import com.v.accounting.R
import com.v.accounting.databinding.FragmentAddAuthorBinding
import com.v.accounting.ui.base.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * Author:v
 * Time:2021/6/3
 *
 * an example demo,just ignore whether it is reasonable
 */
class AddUserFragment : BaseBindingFragment<FragmentAddAuthorBinding>() {

    override fun getRootViewId(): Int {
        return R.layout.fragment_add_author
    }

    override fun initView(root: View) {
        setToolbarTitle(R.string.title_add_user)
        bindingViewModel {
            vm = getViewModel()
        }
    }

}