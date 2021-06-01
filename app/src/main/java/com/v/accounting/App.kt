package com.v.accounting

import android.app.Application
import com.v.accounting.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Author:v
 * Time:2021/6/1
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidContext(this@App)
        modules(appModules)
    }
}