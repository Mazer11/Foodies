package ru.mazer.foodies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.mazer.foodies.ui.navigation.FoodiesNavGraph
import ru.mazer.foodies.ui.theme.FoodiesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodiesTheme {
                val navHostController = rememberNavController()

                FoodiesNavGraph(navHostController = navHostController)
            }
        }
    }
}