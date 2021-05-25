package com.v.accounting.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.v.accounting.data.User
import com.v.accounting.data.UserRepository

/**
 * Author:v
 * Time:2021/5/20
 */
class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun addUser(user: User): LiveData<Boolean> {
        return userRepository.addUser(user)
    }

    fun getUser(id: Int) = userRepository.getUser(id)

    fun getUsers() = userRepository.getUsers()
}