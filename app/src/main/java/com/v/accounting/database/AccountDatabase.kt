package com.v.accounting.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.v.accounting.data.User

/**
 * Author:v
 * Time:2021/6/3
 */
@Database(
    version = 1,
    entities = [User::class],
    exportSchema = true
)
abstract class AccountDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}