package ru.mazer.foodies.ui.screens.catalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.ui.screens.common.TopLine
import ru.mazer.foodies.domain.models.Tag
import ru.mazer.foodies.ui.screens.common.DishCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen() {

    val columnsCount = 2
    val testDishes = listOf(
        Dish(
            id = 9,
            category_id = 676168,
            name = "Митаки 8шт",
            description = "Традиционный ролл с огурцом и нежным сливочным сыром  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = "1.jpg",
            price_current = 23000,
            price_old = 43511,
            measure = 150,
            measure_unit = "г",
            energy_per_100_grams = 302.0,
            proteins_per_100_grams = 6.1,
            fats_per_100_grams = 3.7,
            carbohydrates_per_100_grams = 61.1,
            tag_ids = listOf()
        ),
        Dish(
            id = 14,
            category_id = 676168,
            name = "Радуга 8шт",
            description = "Классический урамаки ролл из нежного лосося, тунца, сливочного сыра и огурца  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = "1.jpg",
            price_current = 51000,
            price_old = null,
            measure = 250,
            measure_unit = "г",
            energy_per_100_grams = 241.5,
            proteins_per_100_grams = 10.1,
            fats_per_100_grams = 3.4,
            carbohydrates_per_100_grams = 42.6,
            tag_ids = listOf(3)
        ),
        Dish(
            id = 1165,
            category_id = 676168,
            name = "Митаки 8шт",
            description = "Традиционный ролл с огурцом и нежным сливочным сыром  Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = "1.jpg",
            price_current = 23000,
            price_old = 43511,
            measure = 150,
            measure_unit = "г",
            energy_per_100_grams = 302.0,
            proteins_per_100_grams = 6.1,
            fats_per_100_grams = 3.7,
            carbohydrates_per_100_grams = 61.1,
            tag_ids = listOf()
        )
    )

    Scaffold(
        topBar = {
            TopLine(
                tags = listOf(
                    Tag(id = 1, name = "Роллы"),
                    Tag(id = 2, name = "Суши"),
                    Tag(id = 3, name = "Наборы"),
                    Tag(id = 4, name = "Горячие блюда")
                ),
                onFilterClick = {

                },
                onSearchClick = {

                }
            )
        },
        containerColor = Color.White
    ) { paddingValues ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(count = columnsCount),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(testDishes){ dish ->
                DishCard(
                    dish = dish,
                    inCartCount = if (dish.id == 14) 2 else 0,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }

}