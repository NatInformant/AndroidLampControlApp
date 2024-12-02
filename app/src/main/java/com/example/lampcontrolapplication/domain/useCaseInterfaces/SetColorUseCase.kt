package com.example.lampcontrolapplication.domain.useCaseInterfaces

interface SetColorUseCase {
    suspend operator fun invoke(colorName:String)
}