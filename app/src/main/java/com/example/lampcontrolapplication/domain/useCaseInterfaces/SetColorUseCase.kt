package com.example.lampcontrolapplication.domain.useCaseInterfaces

import com.example.lampcontrolapplication.data.model.LampSetParameterState
import com.example.lampcontrolapplication.data.state.DataState


interface SetColorUseCase {
    suspend operator fun invoke(colorName:String): DataState<LampSetParameterState>
}