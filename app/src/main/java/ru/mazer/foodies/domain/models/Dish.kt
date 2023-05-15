package ru.mazer.foodies.domain.models

data class Dish(
    //уникальный идентификатор товара
    val id: Int,
    //Название товара
    val name: String,
    //Описание/состав товара
    val description: String,
    //Изображение товара
    val image: String,
    //текущая цена в копейках
    val price_current: Int,
    //старая цена в копейках
    val price_old: Int?,
    //идентификатор категории товара
    val category_id: Int,
    //количество в единицах измерения ниже
    val measure: Int,
    //единица измерения
    val measure_unit: String,
    //количество калорий на 100 г продукта
    val energy_per_100_grams: Double,
    //количество белков на 100 г продукта
    val proteins_per_100_grams: Double,
    //количество жиров на 100 г продукта
    val fats_per_100_grams: Double,
    //количество углеводов на 100 г продукта
    val carbohydrates_per_100_grams: Double,
    //массив атрибутов товара для фильтрации
    val tag_ids: List<Int>,
)