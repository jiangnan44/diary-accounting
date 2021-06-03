package com.v.accounting.ui.addUser

import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.v.accounting.R
import com.v.accounting.data.User
import com.v.accounting.databinding.FragmentAddAuthorBinding
import com.v.accounting.ui.base.BaseVMFragment
import com.v.accounting.utils.ToastManager

/**
 * Author:v
 * Time:2021/6/3
 */
class AddUserFragment : BaseVMFragment<FragmentAddAuthorBinding>() {



    override fun getRootViewId(): Int {
        return R.layout.fragment_add_author
    }

    override fun initView(root: View) {
        binding.btnSave.setOnClickListener {
            addUser()
        }
        binding.ivAvatar.setOnClickListener {
            ToastManager.showShort(context, "choose avatar")
        }
    }

    private fun addUser() {
        val name = binding.etName.text.trim().toString()
        if (TextUtils.isEmpty(name)) {
            ToastManager.showShort(context, "null name!!")
            return
        }

        val phone = binding.etPhone.text.trim().toString()
        if (TextUtils.isEmpty(name)) {
            ToastManager.showShort(context, "null phone!!")
        }

        // TODO: 2021/6/3

    }

    private fun getGender(): Byte {
        val id = binding.rgGender.checkedRadioButtonId
        for (i in 0 until binding.rgGender.childCount) {
            if (binding.rgGender.getChildAt(i).id == id) {
                return i.toByte()
            }
        }
        return 0
    }
}