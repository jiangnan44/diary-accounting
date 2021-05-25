package com.v.accounting.data

/**
 * Author:v
 * Time:2021/5/20
 */
class DaoManager private constructor() {
    var userDao = UserDao()
        private set

    companion object {
        @Volatile
        private var instance: DaoManager? = null

        fun getInstance(): DaoManager {
            return instance ?: synchronized(this) {
                instance ?: DaoManager().also { instance = it }
            }
        }
    }
}