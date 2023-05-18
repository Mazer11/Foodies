package ru.mazer.foodies.domain.usecases

import retrofit2.Call
import ru.mazer.foodies.domain.models.Category
import ru.mazer.foodies.domain.remote.RemoteRepository

class GetCategoriesUseCase(
    private val repository: RemoteRepository
) {

    operator fun invoke(categories: String): Call<List<Category>> {
        return repository.getCategories(categories)
    }

}