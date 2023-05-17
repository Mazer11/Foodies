package ru.mazer.foodies.domain.remote

import retrofit2.Call
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.Tag

class RemoteRepository(
    private val api: RemoteApi
) {

    fun getTags(tags: String): Call<List<Tag>> {
        return api.getTags(tags)
    }

    fun getCategories(categories: String): Call<List<Category>> {
        return api.getCategories(categories)
    }

    fun getProducts(products: String): Call<List<Dish>> {
        return api.getProducts(products)
    }

}