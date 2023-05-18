package ru.mazer.foodies.domain.usecases

import retrofit2.Call
import ru.mazer.foodies.domain.models.Tag
import ru.mazer.foodies.domain.remote.RemoteRepository

class GetTagsUseCase(
    private val repository: RemoteRepository
) {

    operator fun invoke(): Call<List<Tag>> {
        return repository.getTags()
    }

}