package com.example.lampcontrolapplication.di.modules

import com.example.lampcontrolapplication.data.repository.LampRepository
import com.example.lampcontrolapplication.data.repository.LampRepositoryImpl
import com.example.lampcontrolapplication.di.AppComponentScope
import com.example.lampcontrolapplication.domain.useCaseImpl.GetBrightnessLevelsUseCaseImpl
import com.example.lampcontrolapplication.domain.useCaseImpl.GetColorsUseCaseImpl
import com.example.lampcontrolapplication.domain.useCaseImpl.SetBrightnessLevelUseCaseImpl
import com.example.lampcontrolapplication.domain.useCaseImpl.SetColorUseCaseImpl
import com.example.lampcontrolapplication.domain.useCaseImpl.TurnLampOffUseCaseImpl
import com.example.lampcontrolapplication.domain.useCaseImpl.TurnLampOnUseCaseImpl
import com.example.lampcontrolapplication.domain.useCaseInterfaces.GetBrightnessLevelsUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.GetColorsUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.SetBrightnessLevelUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.SetColorUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.TurnLampOffUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.TurnLampOnUseCase
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {
    @Binds
    @AppComponentScope
    fun bindTurnLampOnUseCase(useCase: TurnLampOnUseCaseImpl): TurnLampOnUseCase

    @Binds
    @AppComponentScope
    fun bindTurnLampOffUseCase(useCase: TurnLampOffUseCaseImpl): TurnLampOffUseCase

    @Binds
    @AppComponentScope
    fun bindGetColorsUseCase(useCase: GetColorsUseCaseImpl): GetColorsUseCase

    @Binds
    @AppComponentScope
    fun bindSetColorUseCase(useCase: SetColorUseCaseImpl): SetColorUseCase

    @Binds
    @AppComponentScope
    fun bindGetBrightnessLevelsUseCase(useCase: GetBrightnessLevelsUseCaseImpl): GetBrightnessLevelsUseCase

    @Binds
    @AppComponentScope
    fun bindSetBrightnessLevelUseCase(useCase: SetBrightnessLevelUseCaseImpl): SetBrightnessLevelUseCase

    @Binds
    @AppComponentScope
    fun bindLampRepository(repository: LampRepositoryImpl): LampRepository
}