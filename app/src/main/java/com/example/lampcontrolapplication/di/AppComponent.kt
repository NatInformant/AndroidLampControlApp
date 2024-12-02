package com.example.lampcontrolapplication.di

import com.example.lampcontrolapplication.di.modules.AppBindsModule
import com.example.lampcontrolapplication.di.modules.NetworkModule
import com.example.lampcontrolapplication.di.modules.ViewModelModule
import com.example.lampcontrolapplication.presenter.main.MainFragment
import dagger.Component

@Component(
    modules = [
        ViewModelModule::class,
        AppBindsModule::class,
        NetworkModule::class
    ]
)
@AppComponentScope
interface AppComponent {
    fun inject(fragment: MainFragment)
}

