package ru.mazer.foodies.domain.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dish(
    //уникальный идентификатор товара
    @Json(name = "id") val id: Int,
    //Название товара
    @Json(name = "name") val name: String,
    //Описание/состав товара
    @Json(name = "description") val description: String,
    //Изображение товара
    @Json(name = "image") val image: String,
    //текущая цена в копейках
    @Json(name = "price_current") val price_current: Int,
    //старая цена в копейках
    @Json(name = "price_old") val price_old: Int?,
    //идентификатор категории товара
    @Json(name = "category_id") val category_id: Int,
    //количество в единицах измерения ниже
    @Json(name = "measure") val measure: Int,
    //единица измерения
    @Json(name = "measure_unit") val measure_unit: String,
    //количество калорий на 100 г продукта
    @Json(name = "energy_per_100_grams") val energy_per_100_grams: Double,
    //количество белков на 100 г продукта
    @Json(name = "proteins_per_100_grams") val proteins_per_100_grams: Double,
    //количество жиров на 100 г продукта
    @Json(name = "fats_per_100_grams") val fats_per_100_grams: Double,
    //количество углеводов на 100 г продукта
    @Json(name = "carbohydrates_per_100_grams") val carbohydrates_per_100_grams: Double,
    //массив атрибутов товара для фильтрации
    @Json(name = "tag_ids") val tag_ids: List<Int>,
)