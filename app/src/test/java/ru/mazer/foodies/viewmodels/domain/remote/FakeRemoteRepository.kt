package ru.mazer.foodies.viewmodels.domain.remote

import retrofit2.Call
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.models.PostModel
import ru.mazer.foodies.domain.models.Tag
import ru.mazer.foodies.domain.remote.RemoteRepository

class FakeRemoteRepository: RemoteRepository {
    override fun getTags(): Call<List<Tag>> {
        TODO("Not yet implemented")
    }

    override fun getCategories(): Call<List<Category>> {
        TODO("Not yet implemented")
    }

    override fun getProducts(): Call<List<Dish>> {
        TODO("Not yet implemented")
    }

    override fun postOrder(cartItems: PostModel): Call<String> {
        TODO("Not yet implemented")
    }
}