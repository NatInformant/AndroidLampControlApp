package com.example.lampcontrolapplication.data.repository

import com.example.lampcontrolapplication.data.model.BrightnessLevels
import com.example.lampcontrolapplication.data.model.LampSetParameterState
import com.example.lampcontrolapplication.data.state.DataState

interface LampRepository {
    suspend fun turnLampOn()
    suspend fun turnLampOff()
    suspend fun getColors():DataState<List<String>>
    suspend fun setColor(colorName:String):DataState<LampSetParameterState>
    suspend fun getBrightnessLevels():DataState<BrightnessLevels>
    suspend fun setBrightnessLevel(brightnessLevel:Int):DataState<LampSetParameterState>
}