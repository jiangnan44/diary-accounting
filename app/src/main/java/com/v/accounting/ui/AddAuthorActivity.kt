package com.v.accounting.ui

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.v.accounting.R
import com.v.accounting.data.User
import com.v.accounting.databinding.ActivityAddAuthorBinding
import com.v.accounting.ui.base.BaseVMActivity
import com.v.accounting.utils.ToastManager
import com.v.accounting.vm.InjectUtils
import com.v.accounting.vm.UserViewModel

class AddAuthorActivity : BaseVMActivity() {
    private lateinit var binding: ActivityAddAuthorBinding
    private lateinit var vm: UserViewModel


    override fun initView() {
        binding = setContentViewWithBinding(
            R.layout.activity_add_author,
            ActivityAddAuthorBinding::class
        )
        binding.btnSave.setOnClickListener {
            addUser()
        }
        binding.ivAvatar.setOnClickListener {
            ToastManager.getInstance(this).showShort("choose avatar")
        }
    }

    private fun addUser() {
        val name = binding.etName.text.trim().toString()
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "null name!!", Toast.LENGTH_SHORT).show()
            return
        }

        val phone = binding.etPhone.text.trim().toString()
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "null phone!!", Toast.LENGTH_SHORT).show()
        }

        vm.addUser(User(name, 0).apply {
            this.phone = phone
            this.gender = getGender()
        })

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

    override fun initVM() {
        val factory = InjectUtils.provideUserVMFactory()
        vm = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        vm.getUsers().observe(this, { users ->
            val hint = "we have ${users.size} authors"
            binding.tvNumber.text = hint
        })
    }
}