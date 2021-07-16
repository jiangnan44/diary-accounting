package com.v.accounting.ui.mine.addUser

import android.view.View
import androidx.navigation.fragment.findNavController
import com.v.accounting.R
import com.v.accounting.databinding.FragmentAddAuthorBinding
import com.v.accounting.ui.base.BaseVMFragment
import com.v.accounting.ui.mine.MineViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * Author:v
 * Time:2021/6/3
 *
 * an example demo,just ignore whether it is reasonable
 */
class AddUserFragment : BaseVMFragment<FragmentAddAuthorBinding, MineViewModel>() {

    override fun getRootViewId(): Int {
        return R.layout.fragment_add_author
    }

    override fun initView(root: View) {
        setToolbarTitle(R.string.title_add_user)
        bindingApply {
            vm = getViewModel()
            setupViewModel(vm!!)
        }
    }

    override fun setupViewModel(vm: MineViewModel) {
        vm.apply {
            navigateUp.observe(viewLifecycleOwner, {
                if (it == true) {
                    findNavController().navigateUp()
                    onNavigateUpDone()
                }
            })
        }
    }

}


