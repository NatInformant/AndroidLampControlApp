package com.example.lampcontrolapplication.domain.useCaseInterfaces

import com.example.lampcontrolapplication.data.model.LampSetParameterState
import com.example.lampcontrolapplication.data.state.DataState

interface SetBrightnessLevelUseCase {
    suspend operator fun invoke(brightnessLevel: Int): DataState<LampSetParameterState>
}