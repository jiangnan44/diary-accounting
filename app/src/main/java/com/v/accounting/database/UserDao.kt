package com.v.accounting.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.accounting.data.User

/**
 * Author:v
 * Time:2021/5/20
 */
class UserDao {
    private val userListDb = mutableListOf<User>()
    private val users = MutableLiveData<List<User>>()


    init {
        users.value = userListDb
    }

    fun addUser(user: User): LiveData<Boolean> {
        val ret = MutableLiveData(false)
        // TODO: 2021/5/20 coroutine for real
        userListDb.add(user)
        if (Math.random() > 0.5) {

        } else {
            //update value of users ,which will notify observer
            users.value = userListDb
        }
        return ret
    }

    fun fetchUser(id: Int): User? {
        for (u in userListDb) {
            if (u.id == id) {
                return u
            }
        }
        return null
    }


    //uses LiveData instead of MutableLiveData
    fun fetchUsers(): LiveData<List<User>> {
        return users
    }
}