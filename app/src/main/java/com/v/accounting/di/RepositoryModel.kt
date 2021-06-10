package com.v.accounting.di

import com.v.accounting.repository.UserRepository
import org.koin.dsl.module

/**
 * Author:v
 * Time:2021/6/4
 */
val repositoryModel = module {
    single(createdAtStart = false) {
        UserRepository(get())
    }

}