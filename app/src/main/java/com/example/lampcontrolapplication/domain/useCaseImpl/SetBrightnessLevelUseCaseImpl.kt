package com.example.lampcontrolapplication.domain.useCaseImpl

import com.example.lampcontrolapplication.data.repository.LampRepository
import com.example.lampcontrolapplication.domain.useCaseInterfaces.SetBrightnessLevelUseCase
import javax.inject.Inject

class SetBrightnessLevelUseCaseImpl @Inject constructor(
    private val repository: LampRepository
) : SetBrightnessLevelUseCase {
    override suspend fun invoke(brightnessLevel:Int) =
        repository.setBrightnessLevel(brightnessLevel)
}