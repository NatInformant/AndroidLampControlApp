package com.example.lampcontrolapplication.di

import android.content.Context
import com.example.lampcontrolapplication.App

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> this.appComponent
        else -> applicationContext.appComponent
    }