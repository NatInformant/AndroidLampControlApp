package com.example.lampcontrolapplication.domain.useCaseInterfaces

import com.example.lampcontrolapplication.data.model.BrightnessLevels
import com.example.lampcontrolapplication.data.state.DataState

interface GetBrightnessLevelsUseCase {
    suspend operator fun invoke(): DataState<BrightnessLevels>
}