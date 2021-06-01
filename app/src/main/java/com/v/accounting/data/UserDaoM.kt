package com.v.accounting.data

/**
 * Author:v
 * Time:2021/5/27
 */
class UserDaoM() {
    var userDao = UserDao()
        private set


    companion object {

        @Volatile
        private var instance: UserDaoM? = null

        fun getInstance(): UserDaoM {
            return instance ?: synchronized(this) {
                instance ?: UserDaoM().also { instance = it }
            }
        }
    }

}