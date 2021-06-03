package com.v.accounting.data

import androidx.lifecycle.LiveData
import com.v.accounting.database.UserDao

/**
 * Author:v
 * Time:2021/5/20
 */
class UserRepository private constructor(private val userDao: UserDao) {
    fun addUser(user: User): LiveData<Boolean> {
        //do some logic maybe
        return userDao.addUser(user)
    }

     fun getUsers(): LiveData<List<User>> {
        return userDao.fetchUsers()
    }

    fun getUser(id: Int): User {
        if (id <= 0) {
            return User("", 0)
        }

        //maybe fetch from db-> null->fetch from net

        return userDao.fetchUser(id) ?: User("", 0)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) = instance ?: synchronized(this) {
            instance ?: UserRepository(userDao).also { instance = it }
        }
    }
}