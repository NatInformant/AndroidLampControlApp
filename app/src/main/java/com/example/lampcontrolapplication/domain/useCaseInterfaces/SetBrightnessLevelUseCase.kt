package com.example.lampcontrolapplication.domain.useCaseInterfaces

interface SetBrightnessLevelUseCase {
    suspend operator fun invoke(brightnessLevel: Int)
}