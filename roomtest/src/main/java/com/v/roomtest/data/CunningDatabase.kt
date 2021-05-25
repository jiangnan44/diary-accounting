package com.v.roomtest.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Author:v
 * Time:2021/5/25
 */

@Database(entities = [User::class], version = 1)
abstract class CunningDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}