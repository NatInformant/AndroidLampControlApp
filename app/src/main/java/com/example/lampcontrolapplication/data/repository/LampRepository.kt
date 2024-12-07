package com.example.lampcontrolapplication.data.repository

import com.example.lampcontrolapplication.data.model.BrightnessLevels
import com.example.lampcontrolapplication.data.model.LampSetParameterState

interface LampRepository {
    suspend fun turnLampOn()
    suspend fun turnLampOff()
    suspend fun getColors(): Result<List<String>>
    suspend fun setColor(colorName: String): Result<LampSetParameterState>
    suspend fun getBrightnessLevels(): Result<BrightnessLevels>
    suspend fun setBrightnessLevel(brightnessLevel: Int): Result<LampSetParameterState>
}