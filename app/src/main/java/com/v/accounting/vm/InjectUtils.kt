package com.v.accounting.vm

import com.v.accounting.data.UserDaoM
import com.v.accounting.data.UserRepository

/**
 * Author:v
 * Time:2021/5/20
 */
object InjectUtils {
    fun provideUserVMFactory(): UserViewModelFactory {
        val repository = UserRepository.getInstance(UserDaoM.getInstance().userDao)
        return UserViewModelFactory(repository)
    }
}