package com.example.lampcontrolapplication.domain.useCaseInterfaces

import com.example.lampcontrolapplication.data.model.LampSetParameterState


interface SetColorUseCase {
    suspend operator fun invoke(colorName:String): Result<LampSetParameterState>
}