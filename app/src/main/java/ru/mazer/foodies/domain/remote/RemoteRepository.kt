package ru.mazer.foodies.domain.remote

import retrofit2.Call
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.Tag

class RemoteRepository(
    private val api: RemoteApi
) {

    //Get request for list of exist tags
    fun getTags(): Call<List<Tag>> {
        return api.getTags()
    }

    //Get request for list of exist categories
    fun getCategories(): Call<List<Category>> {
        return api.getCategories()
    }

    //Get request for list of exist products
    fun getProducts(): Call<List<Dish>> {
        return api.getProducts()
    }

}