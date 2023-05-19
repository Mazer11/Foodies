package ru.mazer.foodies.domain.remote

import retrofit2.Call
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.PostModel
import ru.mazer.foodies.domain.models.Tag

class DefaultRemoteRepository(
    private val api: RemoteApi
): RemoteRepository {

    //Get request for list of exist tags
    override fun getTags(): Call<List<Tag>> {
        return api.getTags()
    }

    //Get request for list of exist categories
    override fun getCategories(): Call<List<Category>> {
        return api.getCategories()
    }

    //Get request for list of exist products
    override fun getProducts(): Call<List<Dish>> {
        return api.getProducts()
    }

    //Post user order. Just a sample
    override fun postOrder(cartItems: PostModel): Call<String> {
        return api.postOrder(cartItems)
    }
}