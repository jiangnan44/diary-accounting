package com.v.accounting.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.v.accounting.entity.User

/**
 * Author:v
 * Time:2021/6/3
 */
@Database(
    version = 1,
    entities = [User::class],
    exportSchema = true
)
abstract class AccountDb : RoomDatabase() {
    abstract fun userDao(): UserDao
}