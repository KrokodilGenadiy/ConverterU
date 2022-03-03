package com.zaus_app.converter

import android.app.Application
import com.zaus_app.converter.di.AppComponent
import com.zaus_app.converter.di.DaggerAppComponent
import com.zaus_app.converter.di.modules.DomainModule
import com.zaus_app.moviefrumy.di.modules.DatabaseModule
import com.zaus_app.moviefrumy.di.modules.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .domainModule(DomainModule(this))
            .databaseModule(DatabaseModule())
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}