package ru.mazer.foodies.ui.screens.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mazer.foodies.R
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.ui.screens.common.TopLine
import ru.mazer.foodies.domain.models.Tag
import ru.mazer.foodies.ui.screens.common.DishCard
import ru.mazer.foodies.ui.theme.FoodiesTheme
import ru.mazer.foodies.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen() {

    val columnsCount = 2
    val testCart = hashMapOf<Dish, Int>()
    val lazyGridScrollState = rememberLazyGridState()
    val isScrolled =
        remember { derivedStateOf { lazyGridScrollState.firstVisibleItemScrollOffset > 0 } }
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
                isScrolled = isScrolled.value,
                onFilterClick = {

                },
                onSearchClick = {

                }
            )
        },
        bottomBar = {
            if (testCart.isNotEmpty()) {
                var currentPrice = 0
                testCart.forEach { entry: Map.Entry<Dish, Int> ->
                    currentPrice += entry.key.price_current * entry.value
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = if (isScrolled.value) 24.dp else 0.dp,
                            shape = RectangleShape
                        )
                        .background(color = Color.White)
                ) {
                    Button(
                        onClick = {
                            /*TODO*/
                        },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cart),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "${currentPrice / 100} \u20BD",
                            style = Typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        },
        containerColor = Color.White
    ) { paddingValues ->

        LazyVerticalGrid(
            state = lazyGridScrollState,
            columns = GridCells.Fixed(count = columnsCount),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(testDishes) { dish ->
                DishCard(
                    dish = dish,
                    inCartCount = if (dish.id == 14) 2 else 0,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

    }

}

@Preview
@Composable
fun CatalogScreenPreview() {
    FoodiesTheme {
        CatalogScreen()
    }
}