package com.example.ingresoandroid.services

sealed class ConnectionResponse {
    class OnSuccess(val result: String) : ConnectionResponse()
    class OnFailure : ConnectionResponse()
}