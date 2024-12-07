package com.example.lampcontrolapplication.presenter.state

sealed class UiState<out T> {
    data class Success<out T>(val value: T) : UiState<T>()
    data class Failure(val message: String) : UiState<Nothing>()
    data object Loading : UiState<Nothing>()

    fun <T> Result<T>.toUiState(): UiState<T> {
        return if (this.isSuccess) {
            val result = this.getOrNull()
            result?.let { Success(it) }
                ?: Failure("Data was null")
        } else this.exceptionOrNull()?.let { Failure(it.message ?: "Unknown error") }
            ?: Failure("Unknown error")
    }
}
