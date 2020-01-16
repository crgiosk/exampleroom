package com.example.ingresoandroid.viewmodel

sealed class UIState {
    object Loading : UIState()
    class Success(val data: Any) : UIState()
    class Error(val message: String) : UIState()
}