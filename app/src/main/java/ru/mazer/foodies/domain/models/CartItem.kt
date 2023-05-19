package ru.mazer.foodies.domain.models

//Data for the Cart
data class CartItem(
    //Id of product in cart, that was taken from Dish object
    val id: Int,
    //Count of this dish in the cart
    val count: Int
)
