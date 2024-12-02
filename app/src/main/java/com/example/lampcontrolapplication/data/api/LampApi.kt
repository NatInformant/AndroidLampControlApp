package com.example.lampcontrolapplication.data.api

import com.example.lampcontrolapplication.data.model.BrightnessLevels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LampApi {
    @POST("state/on")
    suspend fun turnLampOn(): Boolean
    @POST("state/off")
    suspend fun turnLampOff(): Boolean

    @POST("color/")
    suspend fun setColor(@Query("color") colorName:String): Boolean
    @GET("color/names_only")
    suspend fun getColors(): Response<List<String>>

    @POST("brightness/")
    suspend fun setBrightnessLevel(@Query("level") brightnessLevel:Int): Boolean
    @GET("brightness/")
    suspend fun getBrightnessLevels(): Response<BrightnessLevels>
}
