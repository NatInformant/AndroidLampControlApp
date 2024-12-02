package com.example.lampcontrolapplication.domain.useCaseImpl

import com.example.lampcontrolapplication.data.repository.LampRepository
import com.example.lampcontrolapplication.domain.useCaseInterfaces.SetColorUseCase
import javax.inject.Inject

class SetColorUseCaseImpl @Inject constructor(
    private val repository: LampRepository
) : SetColorUseCase {
    override suspend fun invoke(colorName: String) =
        repository.setColor(colorName)
}