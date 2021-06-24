package com.v.accounting.repository

import androidx.lifecycle.LiveData
import com.v.accounting.db.UserDao
import com.v.accounting.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Author:v
 * Time:2021/5/20
 */
class UserRepository constructor(private val userDao: UserDao) : BaseRepository() {


    suspend fun addUser(user: User): Boolean = userDao.addUser(user) > 0


    fun getUsers(): LiveData<List<User>> {
        return userDao.allUsers()
    }


    suspend fun getLastUser(): LiveData<User?> = userDao.fetchLastUser()


    //no need define Dispatchers.IO, Room will do the will for us
    suspend fun deleteUser(user: User) = withContext(Dispatchers.IO) {
        userDao.deleteUser(user)
    }

    suspend fun clearTable() {
        userDao.clearTable()
    }

}