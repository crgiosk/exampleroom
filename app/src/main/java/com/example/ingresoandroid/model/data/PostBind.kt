package com.example.ingresoandroid.model.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class PostBind(

    @Json(name = "id")
    @field:Json(name = "id")
    var id: Int = 0,

    @Json(name = "title")
    @field:Json(name = "title")
    var title: String = String(),

    @Json(name = "body")
    @field:Json(name = "body")
    var body: String = String()

): Serializable