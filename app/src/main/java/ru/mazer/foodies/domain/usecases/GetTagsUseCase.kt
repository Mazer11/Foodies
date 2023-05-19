package ru.mazer.foodies.domain.usecases

import android.content.Context
import ru.mazer.foodies.domain.models.Tag
import ru.mazer.foodies.domain.remote.RemoteRepository
import ru.mazer.foodies.utils.JsonUtil

/**
 * Use case for get request for list of tags*/
class GetTagsUseCase(
    //For real server
    private val repository: RemoteRepository,
    //Only while server is not exists
    private val context: Context
) {

    operator fun invoke(): List<Tag> {
        //For real server
        //return repository.getTags().execute().body() ?: listOf()

        //When server is not exists
        return JsonUtil.getAssetTag(context) ?: listOf()
    }

}