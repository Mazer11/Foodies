package ru.mazer.foodies.domain.usecases

import retrofit2.Call
import ru.mazer.foodies.domain.models.PostModel
import ru.mazer.foodies.domain.remote.RemoteRepository

class PostOrderUseCase(
    private val repository: RemoteRepository
) {

    operator fun invoke(order: PostModel): Call<String> {
        return repository.postOrder(order)
    }

}