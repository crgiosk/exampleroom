package com.example.ingresoandroid.model.api

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface PostApi {

    val base: String

    @GET("/posts?userId=")
    fun getPost(): Call<JsonArray>

}