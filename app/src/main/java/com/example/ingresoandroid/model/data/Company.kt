package com.example.ingresoandroid.model.data

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.io.Serializable

@Keep
data class Company(

    @Json(name = "name")
    @field:Json(name = "name")
    val name: String = String(),

    @Json(name = "catchPhrase")
    @field:Json(name = "catchPhrase")
    val catchPhrase: String = String(),

    @Json(name = "bs")
    @field:Json(name = "bs")
    val bs: String = String()
): Serializable