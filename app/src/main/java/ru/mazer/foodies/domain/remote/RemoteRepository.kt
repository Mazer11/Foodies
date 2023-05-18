package ru.mazer.foodies.domain.remote

import retrofit2.Call
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.Tag

class RemoteRepository(
    private val api: RemoteApi
) {

    fun getTags(): Call<List<Tag>> {
        return api.getTags()
    }

    fun getCategories(): Call<List<Category>> {
        return api.getCategories()
    }

    fun getProducts(): Call<List<Dish>> {
        return api.getProducts()
    }

}