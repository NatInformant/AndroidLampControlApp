package com.example.lampcontrolapplication.domain.useCaseInterfaces

import com.example.lampcontrolapplication.data.state.DataState

interface GetColorsUseCase {
    suspend operator fun invoke():DataState<List<String>>
}