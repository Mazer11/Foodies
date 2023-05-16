package ru.mazer.foodies.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.mazer.foodies.ui.screens.cart.components.CartRow
import ru.mazer.foodies.ui.screens.common.FixedButton
import ru.mazer.foodies.ui.theme.FoodiesTheme
import ru.mazer.foodies.ui.theme.Typography
import ru.mazer.foodies.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    navController: NavController,
    vm: MainViewModel
) {
    val cartList = vm.cartList.observeAsState()
    val dishes = vm.dishList.observeAsState()
    var currentPrice = 0
    val isCartEmpty =
        remember { derivedStateOf { cartList.value != null && cartList.value!!.isNotEmpty() } }
    if (isCartEmpty.value)
        cartList.value!!.forEach { cartItem ->
            currentPrice +=
                dishes.value!!.first { it.id == cartItem.id }.price_current * cartItem.count
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Корзина",
                        modifier = Modifier.padding(start = 32.dp)
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Navigate up",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                modifier = Modifier.shadow(elevation = 8.dp)
            )
        },
        bottomBar = {
            if (isCartEmpty.value)
                FixedButton(
                    onClick = {}
                ) {
                    Text(
                        text = "Заказать за ${currentPrice / 100} \u20BD",
                        style = Typography.bodyLarge
                    )
                }
        }
    ) { paddingValues ->
        if (isCartEmpty.value) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(cartList.value!!) { cartItem ->
                    val currentDish = dishes.value!!.first { it.id == cartItem.id }
                    CartRow(
                        dish = currentDish,
                        inCartCount = cartItem.count,
                        onAdd = {
                            vm.addToCart(currentDish)
                        },
                        onRemove = {
                            vm.removeFromCart(currentDish)
                        },
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Text(
                    text = "Пусто, выберите блюда в каталоге :)",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .alpha(.6f)
                        .padding(horizontal = 68.dp)
                )
            }
        }
    }

}

@Preview
@Composable
fun CartScreenPreview() {
    FoodiesTheme {
        val navController = rememberNavController()
        val vm = hiltViewModel<MainViewModel>()
        CartScreen(navController = navController, vm = vm)
    }
}