package ru.mazer.foodies.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(
    //Unique id
    @Json(name = "id") val id: Int,
    //Tag(filter) name
    @Json(name = "name") val name: String
)