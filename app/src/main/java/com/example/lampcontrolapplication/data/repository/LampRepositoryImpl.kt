package com.example.lampcontrolapplication.data.repository

import com.example.lampcontrolapplication.data.api.LampApi
import com.example.lampcontrolapplication.data.model.BrightnessLevels
import com.example.lampcontrolapplication.data.state.DataState
import javax.inject.Inject

class LampRepositoryImpl @Inject constructor(
    private val lampApi: LampApi
) : LampRepository {
    override suspend fun turnLampOn() {
        lampApi.turnLampOn()
    }

    override suspend fun turnLampOff() {
        lampApi.turnLampOff()
    }

    override suspend fun getColors(): DataState<List<String>> {
        kotlin.runCatching {
            lampApi.getColors()
        }.fold(
            onSuccess = { response ->
                if (!response.isSuccessful) {
                    return DataState.Failure(response.message())
                }
                return DataState.Success(response.body()!!)
            },
            onFailure = {
                return DataState.Failure(it.message!!)
            }
        )
    }

    override suspend fun setColor(colorName: String) {
        kotlin.runCatching {
            lampApi.setColor(colorName)
        }.onFailure{
            
        }

    }

    override suspend fun getBrightnessLevels(): DataState<BrightnessLevels> {
        kotlin.runCatching {
            lampApi.getBrightnessLevels()
        }.fold(
            onSuccess = { response ->
                if (!response.isSuccessful) {
                    return DataState.Failure(response.message())
                }
                return DataState.Success(response.body()!!)
            },
            onFailure = {
                return DataState.Failure(it.message!!)
            }
        )
    }

    override suspend fun setBrightnessLevel(brightnessLevel: Int) {
        lampApi.setBrightnessLevel(brightnessLevel)
    }
}