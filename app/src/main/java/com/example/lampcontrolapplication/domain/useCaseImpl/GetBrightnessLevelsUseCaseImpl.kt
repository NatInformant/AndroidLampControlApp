package com.example.lampcontrolapplication.domain.useCaseImpl

import com.example.lampcontrolapplication.data.repository.LampRepository
import com.example.lampcontrolapplication.domain.useCaseInterfaces.GetBrightnessLevelsUseCase
import javax.inject.Inject

class GetBrightnessLevelsUseCaseImpl @Inject constructor(
    private val repository: LampRepository
) : GetBrightnessLevelsUseCase {
    override suspend fun invoke() =
        repository.getBrightnessLevels()
}