package ru.mazer.foodies.ui.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.navigation.NavController
import ru.mazer.foodies.viewmodels.MainViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    vm: MainViewModel
) {
    val searchText = vm.searchText.observeAsState()
    val searchResult = vm.searchList.observeAsState()

    Scaffold(
        topBar = {

        }
    ) { paddingValues ->
        if (searchResult.value != null) {
            Box(modifier = Modifier.padding(paddingValues))
        } else {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Ничего не нашлось :(",
                    modifier = Modifier
                        .alpha(.6f)
                        .align(Alignment.Center)
                )
            }
        }
    }
}