package ru.mazer.foodies.ui.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.mazer.foodies.R
import ru.mazer.foodies.ui.navigation.NavRoutes
import ru.mazer.foodies.ui.screens.common.DishCard
import ru.mazer.foodies.ui.theme.Typography
import ru.mazer.foodies.viewmodels.MainViewModel

/**
 * Screen where user can use search feature*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    vm: MainViewModel
) {
    //Entered search text
    val searchText = vm.searchText.observeAsState("")
    //The result of searching
    val searchResult = vm.searchList.observeAsState()
    //Current cart items
    val cart = vm.cartList.observeAsState()
    val focusManager = LocalFocusManager.current
    //Exist tags
    val tags = vm.filtersList.observeAsState()
    //The number of columns for dishes
    val columnsCount = 2
    //Checks if "Острое" tag is exists
    val isHotTagExists =
        remember { derivedStateOf { tags.value?.firstOrNull { it.name == "Острое" } != null } }
    //Checks if "Вегетарианское блюдо" tag is exists
    val isVegaTagExists =
        remember { derivedStateOf { tags.value?.firstOrNull { it.name == "Вегетарианское блюдо" } != null } }
    val interactionSource = remember { MutableInteractionSource() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(contentAlignment = Alignment.CenterStart) {
                        TextFieldDefaults.DecorationBox(
                            value = searchText.value,
                            innerTextField = {},
                            enabled = true,
                            singleLine = true,
                            visualTransformation = VisualTransformation.None,
                            interactionSource = interactionSource,
                            placeholder = {
                                Text(
                                    text = stringResource(R.string.search_dish),
                                    modifier = Modifier.alpha(.6f)
                                )
                            },
                            container = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                )
                            },
                        )
                        BasicTextField(
                            value = searchText.value,
                            onValueChange = { vm.onSearchTextChanged(it) },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { focusManager.clearFocus() }
                            ),
                            textStyle = Typography.bodyLarge,
                            interactionSource = interactionSource,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .fillMaxWidth()
                        )
                    }
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
                actions = {
                    if (searchText.value.isNotEmpty())
                        IconButton(onClick = {
                            vm.onSearchTextChanged("")
                            focusManager.clearFocus()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clear),
                                contentDescription = "Clear search text",
                                tint = Color.Black.copy(alpha = .6f)
                            )
                        }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                modifier = Modifier.shadow(elevation = 8.dp),
            )
        }
    ) { paddingValues ->
        if (searchText.value.isNotEmpty()) {
            if (!searchResult.value.isNullOrEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = columnsCount),
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    items(searchResult.value!!) { dish ->
                        DishCard(
                            dish = dish,
                            inCartCount = cart.value?.firstOrNull { it.id == dish.id }?.count ?: 0,
                            modifier = Modifier.padding(8.dp),
                            onCardClick = {
                                navController.navigate(route = "${NavRoutes.Dish.route}/${dish.id}")
                            },
                            onAdd = { thisDish ->
                                vm.addToCart(thisDish)
                            },
                            onRemove = { thisDish ->
                                vm.removeFromCart(thisDish)
                            },
                            labels = {
                                if (dish.price_old != null)
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_sales),
                                        contentDescription = "Discount",
                                        modifier = Modifier
                                            .size(28.dp)
                                            .padding(start = 4.dp)
                                    )
                                if (isHotTagExists.value)
                                    if (dish.tag_ids.contains(tags.value!!.first { it.name == "Острое" }.id))
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_hot),
                                            contentDescription = "Spicy",
                                            modifier = Modifier
                                                .size(28.dp)
                                                .padding(start = 4.dp)
                                        )
                                if (isVegaTagExists.value)
                                    if (dish.tag_ids.contains(tags.value!!.first { it.name == "Вегетарианское блюдо" }.id))
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_vegan),
                                            contentDescription = "Vegetables",
                                            modifier = Modifier
                                                .size(28.dp)
                                                .padding(start = 4.dp)
                                        )
                            }
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = stringResource(R.string.empty_search_message),
                        modifier = Modifier
                            .alpha(.6f)
                            .align(Alignment.Center)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.enter_search_text),
                    modifier = Modifier
                        .alpha(.6f)
                        .align(Alignment.Center)
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}