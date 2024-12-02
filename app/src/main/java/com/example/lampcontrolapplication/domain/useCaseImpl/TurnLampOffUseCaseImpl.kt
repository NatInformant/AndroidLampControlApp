package com.example.lampcontrolapplication.domain.useCaseImpl

import com.example.lampcontrolapplication.data.repository.LampRepository
import com.example.lampcontrolapplication.domain.useCaseInterfaces.TurnLampOffUseCase
import javax.inject.Inject

class TurnLampOffUseCaseImpl @Inject constructor(
    private val repository: LampRepository
) : TurnLampOffUseCase {
    override suspend fun invoke() =
        repository.turnLampOff()
}