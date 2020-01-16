package com.example.ingresoandroid.model

import com.example.ingresoandroid.core.Constants
import com.example.ingresoandroid.model.data.User
import com.example.ingresoandroid.services.Connection
import com.example.ingresoandroid.services.ConnectionResponse
import com.example.ingresoandroid.services.Endpoint
import com.example.ingresoandroid.services.ModelResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class UserRepository(val connection: Connection) {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    enum class UsersAction : Endpoint {
        GET_USERS {
            override val base: String get() = Constants.BASE_URL
            override val path: String get() = "/users"
        }
    }

    fun getUsers(completion: (ModelResponse) -> Unit) {
        connection.send(UsersAction.GET_USERS) { response ->
            when(response) {
                is ConnectionResponse.OnFailure -> {
                    val error =  Constants.ERROR_CONNECTION
                    completion(ModelResponse.OnError(error))
                }
                is ConnectionResponse.OnSuccess -> {
                    val type = Types.newParameterizedType(List::class.java, User::class.java)
                    val adapter: JsonAdapter<List<Any>> = moshi.adapter(type)
                    val list: List<Any> = adapter.fromJson(response.result)!!
                    completion(ModelResponse.OnSuccess(list))
                }
            }
        }
    }


}