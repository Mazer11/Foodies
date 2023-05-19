package ru.mazer.foodies.domain.remote

import retrofit2.Call
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.PostModel
import ru.mazer.foodies.domain.models.Tag

interface RemoteRepository {

    //Get request for list of exist tags
    fun getTags(): Call<List<Tag>>

    //Get request for list of exist categories
    fun getCategories(): Call<List<Category>>

    //Get request for list of exist products
    fun getProducts(): Call<List<Dish>>

    fun postOrder(cartItems: PostModel): Call<String>
}