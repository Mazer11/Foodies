package ru.mazer.foodies.domain.usecases.complex

import ru.mazer.foodies.domain.usecases.GetCategoriesUseCase
import ru.mazer.foodies.domain.usecases.GetProductsUseCase
import ru.mazer.foodies.domain.usecases.GetTagsUseCase

data class RemoteUseCases(
    val getTagsUseCase: GetTagsUseCase,
    val getCategoriesUseCase: GetCategoriesUseCase,
    val getProductsUseCase: GetProductsUseCase
)
