package com.example.lampcontrolapplication.di.modules

import com.example.lampcontrolapplication.BuildConfig
import com.example.lampcontrolapplication.data.api.LampApi
import com.example.lampcontrolapplication.di.AppComponentScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @AppComponentScope
    @Provides
    fun provideLampApi(): LampApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LampApi::class.java)
    }
}