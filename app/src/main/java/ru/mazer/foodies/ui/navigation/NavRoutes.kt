package ru.mazer.foodies.ui.navigation

sealed class NavRoutes(val route: String) {

    object Cart : NavRoutes("cart_screen")
    object Catalog : NavRoutes("catalog_screen")
    object Dish : NavRoutes("dish_screen")
    object Splash : NavRoutes("splash_screen")

}
