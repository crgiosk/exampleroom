package com.example.ingresoandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ingresoandroid.model.UserRepository
import com.example.ingresoandroid.model.data.User
import com.example.ingresoandroid.model.data.UserBind
import com.example.ingresoandroid.services.ModelResponse

class UserViewModel(private val userRepository: UserRepository) {

    // Variables Binding
    private val getUserInfoLiveData: MutableLiveData<UIState> = MutableLiveData()

    fun getUserInfoLiveData(): LiveData<UIState> = getUserInfoLiveData

    fun getUsers() {
        getUserInfoLiveData.value = UIState.Loading
        userRepository.getUsers { response ->
            when(response) {
                is ModelResponse.OnSuccess -> {
                    getUserInfoLiveData.postValue(UIState.Success(mapList(response.result as ArrayList<User>)))
                }
                is ModelResponse.OnError -> {
                    val messageError = response.error
                    getUserInfoLiveData.postValue(UIState.Error(messageError))
                }
            }
        }
    }

    private fun mapList(array : ArrayList<User>): MutableList<UserBind> {
        val values: MutableList<UserBind>  = mutableListOf()
        array.map { userApi ->
            val userBind = UserBind()
            userBind.id = userApi.id
            userBind.name = userApi.name
            userBind.phone = userApi.phone
            userBind.email = userApi.email
            values.add(userBind)
        }
        return values
    }
}