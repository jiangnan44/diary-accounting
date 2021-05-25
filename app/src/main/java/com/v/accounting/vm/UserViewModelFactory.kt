package com.v.accounting.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.v.accounting.data.UserRepository

/**
 * Author:v
 * Time:2021/5/20
 */
class UserViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(repository) as T
    }

}