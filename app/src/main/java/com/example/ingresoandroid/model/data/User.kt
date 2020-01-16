package com.example.ingresoandroid.model.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class User(
    @Json(name = "id")
    @field:Json(name = "id")
    val id: Int = 0,

    @Json(name = "name")
    @field:Json(name = "name")
    val name: String = String(),

    @Json(name = "username")
    @field:Json(name = "username")
    val username: String = String(),

    @Json(name = "email")
    @field:Json(name = "email")
    val email: String = String(),

    @Json(name = "address")
    @field:Json(name = "address")
    val address: Address = Address(),

    @Json(name = "phone")
    @field:Json(name = "phone")
    val phone: String = String(),

    @Json(name = "website")
    @field:Json(name = "website")
    val website: String = String(),

    @Json(name = "company")
    @field:Json(name = "company")
    val company: Company = Company()
): Serializable