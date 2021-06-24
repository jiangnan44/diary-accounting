package com.v.accounting

import android.app.Application
import androidx.annotation.StringRes
import com.v.accounting.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import kotlin.properties.Delegates


/**
 * Author:v
 * Time:2021/6/1
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()
//        instance = this
        configureDI()
    }

    private fun configureDI() = startKoin {
        androidContext(this@App)
        modules(appModules)
    }

/*    companion object {
        private var instance: App by Delegates.notNull()

        fun instance() = instance

        fun getString(@StringRes id: Int): String {
            return instance().getString(id)
        }
    }*/
}