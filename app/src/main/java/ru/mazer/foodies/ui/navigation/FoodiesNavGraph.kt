package ru.mazer.foodies.ui.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.mazer.foodies.ui.screens.cart.CartScreen
import ru.mazer.foodies.ui.screens.catalog.CatalogScreen
import ru.mazer.foodies.ui.screens.dish.DishScreen
import ru.mazer.foodies.ui.screens.splash.SplashScreen

@Composable
fun FoodiesNavGraph(
    navHostController: NavHostController
) {

    NavHost(navController = navHostController, startDestination = NavRoutes.Splash.route) {
        composable(route = NavRoutes.Splash.route) {
            SplashScreen(navController = navHostController)
        }
        composable(route = NavRoutes.Catalog.route) {
            val view = LocalView.current
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = Color.Transparent.toArgb()
            }
            CatalogScreen()
        }
        composable(route = NavRoutes.Cart.route) {
            CartScreen()
        }
        composable(route = NavRoutes.Dish.route) {
            DishScreen()
        }
    }

}