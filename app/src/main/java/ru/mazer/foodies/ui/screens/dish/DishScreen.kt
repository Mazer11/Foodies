package ru.mazer.foodies.ui.screens.dish

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.mazer.foodies.R
import ru.mazer.foodies.ui.screens.common.FixedButton
import ru.mazer.foodies.ui.screens.dish.components.ListItem
import ru.mazer.foodies.ui.theme.FoodiesTheme
import ru.mazer.foodies.ui.theme.Typography
import ru.mazer.foodies.viewmodels.MainViewModel

/**
 * Screen with information for specific dish*/
@Composable
fun DishScreen(
    navController: NavController,
    dishId: Int,
    vm: MainViewModel
) {
    val dish = vm.dishList.value?.first { it.id == dishId }
    val listItemModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 13.dp)

    if (dish != null)
        Scaffold(
            bottomBar = {
                FixedButton(
                    onClick = { vm.addToCart(dish) },
                    modifier = Modifier.shadow(
                        elevation = 24.dp,
                        shape = RectangleShape
                    )
                ) {
                    Text(text = stringResource(R.string.to_cart_for) + "${dish.price_current.div(100)} \u20BD")
                }
            },
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    item {
                        Image(
                            painter = painterResource(id = R.drawable.img1),
                            contentDescription = dish.name,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 24.dp)
                        ) {
                            Text(
                                text = dish.name,
                                style = Typography.headlineMedium
                            )
                            Text(
                                text = dish.description,
                                style = Typography.bodyMedium,
                                modifier = Modifier
                                    .alpha(.6f)
                                    .padding(top = 8.dp)
                            )
                        }
                    }
                    if (dish.measure != 0) {
                        item {
                            ListItem(
                                title = stringResource(R.string.weight),
                                count = "${dish.measure} ${dish.measure_unit}",
                                modifier = listItemModifier
                            )
                        }
                        item {
                            ListItem(
                                title = stringResource(R.string.energy),
                                count = "${dish.energy_per_100_grams} ккал",
                                modifier = listItemModifier
                            )
                        }
                        item {
                            ListItem(
                                title = stringResource(R.string.proteins),
                                count = "${dish.proteins_per_100_grams} г",
                                modifier = listItemModifier
                            )
                        }
                        item {
                            ListItem(
                                title = stringResource(R.string.fats),
                                count = "${dish.fats_per_100_grams} г",
                                modifier = listItemModifier
                            )
                        }
                        item {
                            ListItem(
                                title = stringResource(R.string.carbohydrates),
                                count = "${dish.carbohydrates_per_100_grams} г",
                                modifier = listItemModifier
                            )
                        }
                    }
                }
                FloatingActionButton(
                    onClick = { navController.navigateUp() },
                    containerColor = Color.White,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 16.dp, top = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Return to previous screen"
                    )
                }
            }

        }
}

@Preview
@Composable
fun DishScreenPreview() {

    FoodiesTheme {
        val navController = rememberNavController()
        val mainViewModel = hiltViewModel<MainViewModel>()
        DishScreen(navController = navController, dishId = 9, vm = mainViewModel)
    }

}







































