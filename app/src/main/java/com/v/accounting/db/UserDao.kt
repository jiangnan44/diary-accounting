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
    @Update
    suspend fun updateUser(user: User): Int

    @Delete
    suspend fun deleteUser(user: User): Int

    @Query("DELETE FROM User")
    suspend fun clearTable()

    @Query("SELECT * FROM User WHERE id=:id")
    fun fetchUserById(id: Int?): LiveData<User>

    @Query("SELECT * FROM User ORDER BY id DESC LIMIT 1 ")
    fun fetchLastUser(): LiveData<User?>

    @Query("SELECT * FROM User")
    fun allUsers(): LiveData<List<User>>
}

