package com.v.accounting.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.accounting.db.UserDao
import com.v.accounting.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Author:v
 * Time:2021/5/20
 */
class UserRepository constructor(private val userDao: UserDao) : BaseRepository() {
    suspend fun addUser(
        user: User,
        ret: MutableLiveData<Boolean>
    ) = withContext(Dispatchers.IO) {
        //do some logic maybe
        ret.value = userDao.addUser(user) > 0
    }

    fun getUsers(): LiveData<List<User>> {
        return userDao.allUsers()
    }

    fun getUser(id: Int): LiveData<User> {

        //maybe fetch from db-> null->fetch from net

        return userDao.fetchUserById(id)
    }

    suspend fun deleteUser(user: User) = withContext(Dispatchers.IO) {
        userDao.deleteUser(user)
    }

}