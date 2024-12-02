package com.example.lampcontrolapplication.domain.useCaseImpl

import com.example.lampcontrolapplication.data.repository.LampRepository
import com.example.lampcontrolapplication.domain.useCaseInterfaces.GetColorsUseCase
import javax.inject.Inject

class GetColorsUseCaseImpl @Inject constructor(
    private val repository: LampRepository
) : GetColorsUseCase {
    override suspend fun invoke() =
        repository.getColors()
}