package ru.mazer.foodies.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dish(
    //Unique id of dish
    @Json(name = "id") val id: Int,
    //Dish name
    @Json(name = "name") val name: String,
    //Description of dish
    @Json(name = "description") val description: String,
    //Dish image
    @Json(name = "image") val image: String,
    //Current price of dish
    @Json(name = "price_current") val price_current: Int,
    //Old price of dish
    @Json(name = "price_old") val price_old: Int?,
    //Id of this dish category
    @Json(name = "category_id") val category_id: Int,
    //Weight of this dish
    @Json(name = "measure") val measure: Int,
    //Measure value
    @Json(name = "measure_unit") val measure_unit: String,
    @Json(name = "energy_per_100_grams") val energy_per_100_grams: Double,
    @Json(name = "proteins_per_100_grams") val proteins_per_100_grams: Double,
    @Json(name = "fats_per_100_grams") val fats_per_100_grams: Double,
    @Json(name = "carbohydrates_per_100_grams") val carbohydrates_per_100_grams: Double,
    //List of tags ids of this dish
    @Json(name = "tag_ids") val tag_ids: List<Int>,
)