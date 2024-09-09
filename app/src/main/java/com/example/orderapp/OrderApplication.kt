package com.example.orderapp

import android.app.Application
import com.example.orderapp.di.dbModules
import com.example.orderapp.di.networkModules
import com.example.orderapp.di.repositoryModule
import com.example.orderapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OrderApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@OrderApplication)
            modules(listOf(dbModules, networkModules, repositoryModule, viewModelModule))
        }
    }
}