package ru.mazer.foodies.ui.screens.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.mazer.foodies.viewmodels.MainViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    vm: MainViewModel
) {
    Text(text = "Search")
}