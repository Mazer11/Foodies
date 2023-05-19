package ru.mazer.foodies.domain.usecases

import android.content.Context
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.remote.RemoteRepository
import ru.mazer.foodies.utils.JsonUtil

/**
 * Use case for get request for list of categories*/
class GetCategoriesUseCase(
    //For real server
    private val repository: RemoteRepository,
    //Only while server is not exists
    private val context: Context
) {

    operator fun invoke(): List<Category> {
        //For real server
        //return repository.getCategories().execute().body() ?: listOf()

        //When server is not exists
        return JsonUtil.getAssetCategory(context) ?: listOf()
    }

}