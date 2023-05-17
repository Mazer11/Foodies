package ru.mazer.foodies.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    //уникальный идентификатор категории
    @Json(name = "id") val id: Int,
    //название категории
    @Json(name = "name") val name: String
)