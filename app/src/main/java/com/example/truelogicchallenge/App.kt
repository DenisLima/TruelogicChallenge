package com.example.truelogicchallenge

import android.app.Application
import com.example.truelogicchallenge.data.di.dataModule
import com.example.truelogicchallenge.domain.di.domainModule
import com.example.truelogicchallenge.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule, domainModule, presentationModule))
        }
    }
}