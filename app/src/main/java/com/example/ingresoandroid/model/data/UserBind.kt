package com.example.ingresoandroid.model.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class UserBind(

    @Json(name = "id")
    @field:Json(name = "id")
    var id: Int = 0,

    @Json(name = "name")
    @field:Json(name = "name")
    var name: String = String(),

    @Json(name = "email")
    @field:Json(name = "email")
    var email: String = String(),

    @Json(name = "phone")
    @field:Json(name = "phone")
    var phone: String = String()

): Serializable