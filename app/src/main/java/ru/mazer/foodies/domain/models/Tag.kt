package ru.mazer.foodies.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(
    //уникальный идентификатор атрибута
    @Json(name = "id") val id: Int,
    //название атрибута
    @Json(name = "name") val name: String
)