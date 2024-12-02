package com.example.lampcontrolapplication.domain.useCaseImpl

import com.example.lampcontrolapplication.data.repository.LampRepository
import com.example.lampcontrolapplication.domain.useCaseInterfaces.TurnLampOnUseCase
import javax.inject.Inject

class TurnLampOnUseCaseImpl @Inject constructor(
    private val repository: LampRepository
) : TurnLampOnUseCase {
    override suspend fun invoke() =
        repository.turnLampOn()
}