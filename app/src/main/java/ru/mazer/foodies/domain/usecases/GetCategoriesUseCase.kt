package ru.mazer.foodies.domain.usecases

import ru.mazer.foodies.FoodiesApp
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.remote.RemoteRepository
import ru.mazer.foodies.utils.JsonUtil

class GetCategoriesUseCase(
    //For real server
    private val repository: RemoteRepository,
    //Only while server is not exists
    private val app: FoodiesApp
) {

    operator fun invoke(): List<Category> {
        //For real server
        //return repository.getCategories().execute().body() ?: listOf()

        //When server is not exists
        return JsonUtil.getAssetCategory(app) ?: listOf()
    }

}