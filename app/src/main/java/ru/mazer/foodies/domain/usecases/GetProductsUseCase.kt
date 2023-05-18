package ru.mazer.foodies.domain.usecases

import retrofit2.Call
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.remote.RemoteRepository

class GetProductsUseCase(
    private val repository: RemoteRepository
) {

    operator fun invoke(): Call<List<Dish>> {
        return repository.getProducts()
    }

}