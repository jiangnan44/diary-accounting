package com.v.accounting.di

import androidx.room.Room
import com.v.accounting.R
import com.v.accounting.db.AccountDb
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * Author:v
 * Time:2021/6/1
 */
val databaseModule = module {
    single {
        Room
            .databaseBuilder(
                androidApplication(),
                AccountDb::class.java,
                androidApplication().getString(R.string.db_name)
            )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    single(createdAtStart = false) {
        get<AccountDb>().userDao()
    }
}