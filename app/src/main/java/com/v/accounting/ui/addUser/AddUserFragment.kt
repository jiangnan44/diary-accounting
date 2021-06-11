package com.v.accounting.ui.addUser

import android.view.View
import com.v.accounting.R
import com.v.accounting.databinding.FragmentAddAuthorBinding
import com.v.accounting.ui.base.BaseBindingFragment
import com.v.accounting.ui.vm.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Author:v
 * Time:2021/6/3
 *
 * a example demo,just ignore whether this is reasonable
 */
class AddUserFragment : BaseBindingFragment<FragmentAddAuthorBinding>() {
    val viewModel: UserViewModel by viewModel()

    override fun getRootViewId(): Int {
        return R.layout.fragment_add_author
    }

    override fun initView(root: View) {
        setToolbarTitle(R.string.title_add_user)
//        binding.btnSave.setOnClickListener {
//            addUser()
//        }
//        binding.ivAvatar.setOnClickListener {
//            ToastManager.showShort(context, "choose avatar")
//        }
    }

    private fun addUser() {
//        val name = binding.etName.text.trim().toString()
//        if (TextUtils.isEmpty(name)) {
//            ToastManager.showShort(context, "null name!!")
//            return
//        }
//
//        val phone = binding.etPhone.text.trim().toString()
//        if (TextUtils.isEmpty(name)) {
//            ToastManager.showShort(context, "null phone!!")
//        }

        // TODO: 2021/6/3

    }

    private fun getGender(): Byte {
//        val id = binding.rgGender.checkedRadioButtonId
//        for (i in 0 until binding.rgGender.childCount) {
//            if (binding.rgGender.getChildAt(i).id == id) {
//                return i.toByte()
//            }
//        }
        return 0
    }
}