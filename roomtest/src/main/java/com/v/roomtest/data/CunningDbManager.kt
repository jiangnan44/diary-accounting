package com.v.roomtest.data

import android.content.Context
import android.util.Log
import androidx.room.Room

/**
 * Author:v
 * Time:2021/5/25
 */
class CunningDbManager private constructor() {
    companion object {
        private const val TAG = "roomManager"
        const val DB_NAME = "user.db"
        const val TABLE_USER = "users"

        @Volatile
        private var instance: CunningDatabase? = null

        fun getInstance(context: Context): CunningDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, CunningDatabase::class.java, DB_NAME)
                    .allowMainThreadQueries()//carefully use this
                    .build()
                    .also { instance = it }
            }
        }

        @JvmStatic
        fun addUser(user: User, context: Context) {
            synchronized(this) {
                Log.d(TAG, "add user $user")
                getInstance(context.applicationContext).userDao().insert(user)
            }
        }

        fun getUser(userName: String?, context: Context?): User? {
            if (userName.isNullOrEmpty() || context == null) {
                return null
            }
            synchronized(this) {
                return getInstance(context.applicationContext).userDao().findUserByName(userName)
            }
        }

        fun deleteUser(id: Int, context: Context) {
            synchronized(this) {
                getInstance(context.applicationContext).userDao().deleteById(id)
            }
        }

        fun updateUser(context: Context, user: User) {
            synchronized(this) {
                getInstance(context.applicationContext).userDao().update(user)
            }
        }

        fun getAllUser(context: Context): List<User?>? {
            synchronized(this) {
                return getInstance(context.applicationContext).userDao().allUser
            }
        }

        fun test(context: Context) {
        }
    }


}