package com.example.lampcontrolapplication.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lampcontrolapplication.data.model.BrightnessLevels
import com.example.lampcontrolapplication.data.model.LampSetParameterState
import com.example.lampcontrolapplication.data.model.LampState
import com.example.lampcontrolapplication.domain.useCaseInterfaces.GetBrightnessLevelsUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.GetColorsUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.SetBrightnessLevelUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.SetColorUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.TurnLampOffUseCase
import com.example.lampcontrolapplication.domain.useCaseInterfaces.TurnLampOnUseCase
import com.example.lampcontrolapplication.presenter.state.UiState
import com.example.lampcontrolapplication.presenter.state.UiState.Loading.toUiState
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

    private val _currentLampState = MutableLiveData(LampState.LAMP_OFF)
    val currentLampState: LiveData<LampState>
        get() = _currentLampState

    private val _colors = MutableLiveData<UiState<List<String>>>(UiState.Loading)
    val colors: LiveData<UiState<List<String>>>
        get() = _colors

    private val _setColorResponseState =
        MutableLiveData<UiState<LampSetParameterState>>(UiState.Loading)
    val setColorResponseState: LiveData<UiState<LampSetParameterState>>
        get() = _setColorResponseState

    private val _brightnessLevels = MutableLiveData<UiState<BrightnessLevels>>(UiState.Loading)
    val brightnessLevels: LiveData<UiState<BrightnessLevels>>
        get() = _brightnessLevels

    private val _setBrightnessLevelResponseState =
        MutableLiveData<UiState<LampSetParameterState>>(UiState.Loading)
    val setBrightnessLevelResponseState: LiveData<UiState<LampSetParameterState>>
        get() = _setBrightnessLevelResponseState

    fun changeCurrentLampState() {
        viewModelScope.launch {
            when (_currentLampState.value) {
                LampState.LAMP_ON -> {
                    turnLampOff()
                    setLampStateToLampOff()
                }

                else -> {
                    turnLampOn()
                    setLampStateToLampOn()
                }
            }
        }
    }

    fun setLampStateToLampOn() {
        _currentLampState.postValue(LampState.LAMP_ON)
    }

    fun setLampStateToLampOff() {
        _currentLampState.postValue(LampState.LAMP_OFF)
    }


    private fun getColorsList() {
        viewModelScope.launch {
            val result = getLampColors()
            _colors.postValue(result.toUiState())
        }
    }

    fun setColor(colorName: String) {
        viewModelScope.launch {
            val result = setLampColor(colorName)
            _setColorResponseState.postValue(result.toUiState())
        }
    }

    private fun getLampBrightnessLevels() {
        viewModelScope.launch {
            val result = getBrightnessLevels()
            _brightnessLevels.postValue(result.toUiState())
        }
    }

    fun setLampBrightnessLevel(brightnessLevel: Int) {
        viewModelScope.launch {
            val result = setBrightnessLevel(brightnessLevel)
            _setBrightnessLevelResponseState.postValue(result.toUiState())
        }
    }
}