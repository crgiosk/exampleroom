package com.example.ingresoandroid.model.api

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET

interface UserApi {

    @GET("/users")
    fun getUsers(): Call<JsonArray>

}