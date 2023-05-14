package ru.mazer.foodies.ui.screens.catalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.mazer.foodies.ui.screens.common.TopLine
import ru.mazer.foodies.domain.models.Tag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen() {

    val columnsCount = 2

    Scaffold(
        topBar = {
            TopLine(
                tags = listOf(
                    Tag(id = 1, name = "Роллы"),
                    Tag(id = 2, name = "Суши"),
                    Tag(id = 3, name = "Наборы"),
                    Tag(id = 4, name = "Горячие блюда"),
                    Tag(id = 5, name = "Test"),
                    Tag(id = 6, name = "Test"),
                    Tag(id = 7, name = "Test"),
                    Tag(id = 8, name = "Test"),
                    Tag(id = 9, name = "Test"),
                    Tag(id = 10, name = "Test"),
                    Tag(id = 11, name = "Test"),
                    Tag(id = 12, name = "Test"),
                ),
                onFilterClick = {

                },
                onSearchClick = {

                }
            )
        },
    ) { paddingValues ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = columnsCount),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

        }

    }

}