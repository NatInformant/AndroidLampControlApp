package com.example.lampcontrolapplication.domain.useCaseInterfaces

import com.example.lampcontrolapplication.data.model.BrightnessLevels

interface GetBrightnessLevelsUseCase {
    suspend operator fun invoke(): Result<BrightnessLevels>
}