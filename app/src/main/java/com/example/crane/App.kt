package com.example.crane

import android.app.Application
import android.content.Context
import com.example.crane.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class App : Application() {

    companion object {
        lateinit var appContext: Context
    }


    override fun onCreate() {
        super.onCreate()
        setupTimber()
        Timber.i("onCreate")

        appContext = applicationContext

        startKoin {
            androidContext(this@App)
            modules(modules)
        }

    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }


}