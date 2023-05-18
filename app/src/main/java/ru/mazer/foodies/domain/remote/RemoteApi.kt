package ru.mazer.foodies.domain.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.Tag

interface RemoteApi {

    @GET("/get/tags")
    @Headers("Content-Type: application/json")
    fun getTags(): Call<List<Tag>>

    @GET("/get/categories")
    @Headers("Content-Type: application/json")
    fun getCategories(): Call<List<Category>>

    @GET("/get/products")
    @Headers("Content-Type: application/json")
    fun getProducts(): Call<List<Dish>>

}