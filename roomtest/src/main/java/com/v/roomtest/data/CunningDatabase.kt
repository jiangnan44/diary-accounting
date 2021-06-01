package com.v.roomtest.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Author:v
 * Time:2021/5/25
 */

@Database(
    version = 1,
    entities = [User::class, Book::class],
    views = [BookLoanDetail::class],
    exportSchema = true
  /*  autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]*/
)
abstract class CunningDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}