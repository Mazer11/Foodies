package ru.mazer.foodies.ui.navigation

//Application navigation routes
sealed class NavRoutes(val route: String) {

    object Cart : NavRoutes("cart_screen")
    object Catalog : NavRoutes("catalog_screen")
    object Dish : NavRoutes("dish_screen")
    object Splash : NavRoutes("splash_screen")
    object Search: NavRoutes("search_screen")

}
