package ru.mazer.foodies.domain.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.Tag

interface RemoteApi {

    @GET("/get/{tags}")
    @Headers("Content-Type: application/json")
    fun getTags(
        @Path("tags") tags: String
    ): Call<List<Tag>>

    @GET("/get/{categories}")
    @Headers("Content-Type: application/json")
    fun getCategories(
        @Path("categories") categories: String
    ): Call<List<Category>>

    @GET("/get/{products}")
    @Headers("Content-Type: application/json")
    fun getProducts(
        @Path("products") products: String
    ): Call<List<Dish>>

}