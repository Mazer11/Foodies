package ru.mazer.foodies.ui.screens.catalog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.mazer.foodies.R
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.ui.screens.common.TopLine
import ru.mazer.foodies.domain.models.Tag
import ru.mazer.foodies.ui.navigation.NavRoutes
import ru.mazer.foodies.ui.screens.common.DishCard
import ru.mazer.foodies.ui.screens.common.FixedButton
import ru.mazer.foodies.ui.theme.FoodiesTheme
import ru.mazer.foodies.ui.theme.Typography
import ru.mazer.foodies.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    navController: NavController,
    vm: MainViewModel
) {

    val columnsCount = 2
    val testCart = hashMapOf<Dish, Int>()
    val lazyGridScrollState = rememberLazyGridState()
    val isScrolled =
        remember { derivedStateOf { lazyGridScrollState.firstVisibleItemScrollOffset > 0 } }
    val testDishes = vm.dishList.observeAsState()

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
                    navController.navigate(NavRoutes.Search.route)
                }
            )
        },
        bottomBar = {
            if (testCart.isNotEmpty()) {
                var currentPrice = 0
                testCart.forEach { entry: Map.Entry<Dish, Int> ->
                    currentPrice += entry.key.price_current * entry.value
                }
                FixedButton(
                    onClick = {}
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
            if (!testDishes.value.isNullOrEmpty())
                items(testDishes.value!!) { dish ->
                    DishCard(
                        dish = dish,
                        inCartCount = if (dish.id == 14) 2 else 0,
                        modifier = Modifier.padding(8.dp),
                        onCardClick = {
                            navController.navigate(route = "${NavRoutes.Dish.route}/${dish.id}")
                        }
                    )
                }
        }

    }

}

@Preview
@Composable
fun CatalogScreenPreview() {
    FoodiesTheme {
        val navController = rememberNavController()
        val mainViewModel = MainViewModel()
        CatalogScreen(
            navController,
            mainViewModel
        )
    }
}