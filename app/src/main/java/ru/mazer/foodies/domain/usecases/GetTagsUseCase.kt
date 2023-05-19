package ru.mazer.foodies.domain.usecases

import ru.mazer.foodies.FoodiesApp
import ru.mazer.foodies.domain.models.Tag
import ru.mazer.foodies.domain.remote.RemoteRepository
import ru.mazer.foodies.utils.JsonUtil

class GetTagsUseCase(
    //For real server
    private val repository: RemoteRepository,
    //Only while server is not exists
    private val app: FoodiesApp
) {

    operator fun invoke(): List<Tag> {
        //For real server
        //return repository.getTags().execute().body() ?: listOf()

        //When server is not exists
        return JsonUtil.getAssetTag(app) ?: listOf()
    }

}