package ru.mazer.foodies.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    //Unique id of category
    @Json(name = "id") val id: Int,
    //Name of category
    @Json(name = "name") val name: String
)