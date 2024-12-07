package com.example.lampcontrolapplication.domain.useCaseInterfaces

import com.example.lampcontrolapplication.data.model.LampSetParameterState

interface SetBrightnessLevelUseCase {
    suspend operator fun invoke(brightnessLevel: Int): Result<LampSetParameterState>
}