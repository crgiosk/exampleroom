package com.example.ingresoandroid.model.data

import androidx.annotation.Keep
import com.example.ingresoandroid.model.data.Geo
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Address(

    @Json(name = "street")
    @field:Json(name = "street")
    val street: String = String(),

    @Json(name = "suite")
    @field:Json(name = "suite")
    val suite: String = String(),

    @Json(name = "city")
    @field:Json(name = "city")
    val city: String = String(),

    @Json(name = "zipcode")
    @field:Json(name = "zipcode")
    val zipcode: String = String(),

    @Json(name = "geo")
    @field:Json(name = "geo")
    val geo: Geo = Geo()
): Serializable