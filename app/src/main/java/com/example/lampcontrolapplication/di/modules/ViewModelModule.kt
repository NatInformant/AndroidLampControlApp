package com.example.lampcontrolapplication.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lampcontrolapplication.di.AppComponentScope
import com.example.lampcontrolapplication.di.viewModel.ViewModelFactory
import com.example.lampcontrolapplication.di.viewModel.ViewModelKey
import com.example.lampcontrolapplication.presenter.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @AppComponentScope
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}