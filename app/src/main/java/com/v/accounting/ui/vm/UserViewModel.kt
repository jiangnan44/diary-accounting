package com.v.accounting.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.v.accounting.entity.User
import com.v.accounting.repository.UserRepository
import com.v.accounting.ui.base.BaseViewModel

/**
 * Author:v
 * Time:2021/6/3
 */
class UserViewModel(private val userRepository: UserRepository) : BaseViewModel() {


    fun addUser(user: User) = launchOnViewModelScope {
        MutableLiveData<Boolean>().also {
            userRepository.addUser(user, it)
        }
    }

    fun getUser(id: Int) = userRepository.getUser(id)
}