package com.v.accounting.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.v.accounting.entity.User

/**
 * Author:v
 * Time:2021/5/20
 */

@Dao
interface UserDao {
    @Insert
    suspend fun addUser(user: User): Long

    @Delete
    suspend fun deleteUser(user: User): Int

    @Update
    suspend fun updateUser(user: User): Int

    @Query("SELECT * FROM User WHERE id=:id")
    fun fetchUserById(id: Int?): LiveData<User>

    @Query("SELECT * FROM User")
    fun allUsers(): LiveData<List<User>>
}

