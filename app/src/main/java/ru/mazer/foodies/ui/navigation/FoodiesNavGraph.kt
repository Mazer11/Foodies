package ru.mazer.foodies.ui.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.mazer.foodies.ui.screens.cart.CartScreen
import ru.mazer.foodies.ui.screens.catalog.CatalogScreen
import ru.mazer.foodies.ui.screens.dish.DishScreen
import ru.mazer.foodies.ui.screens.search.SearchScreen
import ru.mazer.foodies.ui.screens.splash.SplashScreen
import ru.mazer.foodies.viewmodels.MainViewModel

//Application Navigation Graph
@Composable
fun FoodiesNavGraph(
    navHostController: NavHostController
) {

    val startDestination = NavRoutes.Splash.route

    NavHost(navController = navHostController, startDestination = "main_graph") {
        //Uses navigation bloc to provide the same MainViewModel object to screens
        navigation(
            startDestination = startDestination,
            route = "main_graph"
        ) {
            composable(route = NavRoutes.Splash.route) {
                SplashScreen(navController = navHostController)
            }
            composable(route = NavRoutes.Catalog.route) { backStackEntry ->
                val view = LocalView.current
                val parentEntry = remember(backStackEntry) {
                    navHostController.getBackStackEntry("main_graph")
                }
                val viewModel = hiltViewModel<MainViewModel>(parentEntry)
                SideEffect {
                    val window = (view.context as Activity).window
                    window.statusBarColor = Color.White.toArgb()
                }
                CatalogScreen(
                    navController = navHostController,
                    vm = viewModel
                )
            }
            composable(route = NavRoutes.Cart.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navHostController.getBackStackEntry("main_graph")
                }
                val viewModel = hiltViewModel<MainViewModel>(parentEntry)
                CartScreen(
                    navController = navHostController,
                    vm = viewModel
                )
            }
            //Passes the dish id through navigation
            composable(
                route = NavRoutes.Dish.route + "/{id}",
                arguments = listOf(navArgument("id") {})
            ) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navHostController.getBackStackEntry("main_graph")
                }
                val viewModel = hiltViewModel<MainViewModel>(parentEntry)
                DishScreen(
                    navController = navHostController,
                    dishId = backStackEntry.arguments?.getString("id")?.toInt() ?: 0,
                    vm = viewModel
                )
            }
            composable(route = NavRoutes.Search.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navHostController.getBackStackEntry("main_graph")
                }
                val viewModel = hiltViewModel<MainViewModel>(parentEntry)
                SearchScreen(
                    navController = navHostController,
                    vm = viewModel
                )
            }
        }
    }

}