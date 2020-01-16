package com.example.ingresoandroid.services

sealed class ModelResponse {
    class OnSuccess(val result: Any) : ModelResponse()
    class OnError(val error: String) : ModelResponse()
}