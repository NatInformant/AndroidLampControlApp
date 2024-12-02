package com.example.lampcontrolapplication.data.state

sealed class DataState<out T> {
    data class Success<out T>(val value: T) : DataState<T>()
    data class Failure(val message: String) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
}
