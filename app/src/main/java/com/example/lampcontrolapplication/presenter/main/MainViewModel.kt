package com.example.lampcontrolapplication.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lampcontrolapplication.data.model.BrightnessLevels
import com.example.lampcontrolapplication.data.model.LampState
import com.example.lampcontrolapplication.data.state.DataState
import com.example.lampcontrolapplication.domain.useCaseInterfaces.GetBrightnessLevelsUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.GetColorsUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.SetBrightnessLevelUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.SetColorUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.TurnLampOffUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.TurnLampOnUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val turnLampOn: TurnLampOnUseCase,
    private val turnLampOff: TurnLampOffUseCase,
    private val getLampColors: GetColorsUseCase,
    private val setLampColor: SetColorUseCase,
    private val getBrightnessLevels: GetBrightnessLevelsUseCase,
    private val setBrightnessLevel: SetBrightnessLevelUseCase
) : ViewModel() {
    init {
        getColorsList()
        getLampBrightnessLevels()
    }

    private var _currentLampState = MutableLiveData(LampState.LAMP_OFF)
    val currentLampState: LiveData<LampState>
        get() = _currentLampState

    private var _colors = MutableLiveData<DataState<List<String>>>(DataState.Loading)
    val colors: LiveData<DataState<List<String>>>
        get() = _colors

    private var _brightnessLevels = MutableLiveData<DataState<BrightnessLevels>>(DataState.Loading)
    val brightnessLevels: LiveData<DataState<BrightnessLevels>>
        get() = _brightnessLevels

    fun changeCurrentLampState() {
        viewModelScope.launch {
            when (_currentLampState.value) {
                LampState.LAMP_ON -> {
                    turnLampOff()
                    _currentLampState.postValue(LampState.LAMP_OFF)
                }

                else -> {
                    turnLampOn()
                    _currentLampState.postValue(LampState.LAMP_ON)
                }
            }
        }
    }

    private fun getColorsList() {
        viewModelScope.launch {
            val result = getLampColors()
            _colors.postValue(result)
        }
    }

    fun setColor(colorName: String) {
        viewModelScope.launch {
            setLampColor(colorName)
        }
    }

    private fun getLampBrightnessLevels() {
        viewModelScope.launch {
            val result = getBrightnessLevels()
            _brightnessLevels.postValue(result)
        }
    }

    fun setLampBrightnessLevel(brightnessLevel: Int) {
        viewModelScope.launch {
            setBrightnessLevel(brightnessLevel)
        }
    }
}