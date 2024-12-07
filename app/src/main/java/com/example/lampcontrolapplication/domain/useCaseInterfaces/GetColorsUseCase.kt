package com.example.lampcontrolapplication.domain.useCaseInterfaces

interface GetColorsUseCase {
    suspend operator fun invoke():Result<List<String>>
}