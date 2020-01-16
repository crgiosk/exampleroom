package com.example.ingresoandroid.model.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Geo(

    @Json(name = "lat")
    @field:Json(name = "lat")
    val lat: String = String(),

    @Json(name = "lng")
    @field:Json(name = "lng")
    val lng: String = String()
): Serializable