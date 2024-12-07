package com.example.lampcontrolapplication.data.repository

import com.example.lampcontrolapplication.data.api.LampApi
import com.example.lampcontrolapplication.data.model.BrightnessLevels
import com.example.lampcontrolapplication.data.model.LampSetParameterState
import retrofit2.HttpException
import javax.inject.Inject

class LampRepositoryImpl @Inject constructor(
    private val lampApi: LampApi
) : LampRepository {
    override suspend fun turnLampOn() {
        kotlin.runCatching {
            lampApi.turnLampOn()
        }
    }

    override suspend fun turnLampOff() {
        kotlin.runCatching {
            lampApi.turnLampOff()
        }
    }

    override suspend fun getColors(): Result<List<String>> {
        kotlin.runCatching {
            lampApi.getColors()
        }.fold(
            onSuccess = { response ->
                if (!response.isSuccessful) {
                    return Result.failure(HttpException(response))
                }
                return Result.success(response.body()!!)
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    override suspend fun setColor(colorName: String): Result<LampSetParameterState> {
        kotlin.runCatching {
            lampApi.setColor(colorName)
        }.fold(
            onSuccess = { response ->
                if (!response.isSuccessful && response.code() == 400) {
                    return Result.success(LampSetParameterState.FAILURE_LAMP_OFF)
                }
                if (!response.isSuccessful) {
                    return Result.failure(HttpException(response))
                }

                return Result.success(LampSetParameterState.SUCCESS)
            },
            onFailure = {
                return Result.failure(it)
            }
        )

    }

    override suspend fun getBrightnessLevels(): Result<BrightnessLevels> {
        kotlin.runCatching {
            lampApi.getBrightnessLevels()
        }.fold(
            onSuccess = { response ->
                if (!response.isSuccessful) {
                    return Result.failure(HttpException(response))
                }
                return Result.success(response.body()!!)
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    override suspend fun setBrightnessLevel(brightnessLevel: Int): Result<LampSetParameterState> {
        kotlin.runCatching {
            lampApi.setBrightnessLevel(brightnessLevel)
        }.fold(
            onSuccess = { response ->
                if (!response.isSuccessful && response.code() == 400) {
                    return Result.success(LampSetParameterState.FAILURE_LAMP_OFF)
                }
                if (!response.isSuccessful) {
                    return Result.failure(HttpException(response))
                }

                return Result.success(LampSetParameterState.SUCCESS)
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }
}