package com.example.lampcontrolapplication

import android.app.Application
import com.example.lampcontrolapplication.di.AppComponent
import com.example.lampcontrolapplication.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}