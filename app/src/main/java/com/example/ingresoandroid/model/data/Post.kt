package com.example.ingresoandroid.model.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Post(

    @Json(name = "userId")
    @field:Json(name = "userId")
    val userId: Int = 0,

    @Json(name = "id")
    @field:Json(name = "id")
    val id: Int = 0,

    @Json(name = "title")
    @field:Json(name = "title")
    val title: String = String(),

    @Json(name = "body")
    @field:Json(name = "body")
    val body: String = String()

): Serializable