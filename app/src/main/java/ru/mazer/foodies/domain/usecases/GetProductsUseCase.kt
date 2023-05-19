package ru.mazer.foodies.domain.usecases

import ru.mazer.foodies.FoodiesApp
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.domain.remote.RemoteRepository
import ru.mazer.foodies.utils.JsonUtil

class GetProductsUseCase(
    //For real server
    private val repository: RemoteRepository,
    //Only while server is not exists
    private val app: FoodiesApp
) {

    operator fun invoke(): List<Dish> {
        //For real server
        //return repository.getProducts().execute().body() ?: listOf()

        //When server is not exists
        return JsonUtil.getAssetDish(app) ?: listOf()
    }

}