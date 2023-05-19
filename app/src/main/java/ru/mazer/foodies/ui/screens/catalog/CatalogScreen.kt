package ru.mazer.foodies.ui.screens.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import ru.mazer.foodies.R
import ru.mazer.foodies.ui.screens.common.TopLine
import ru.mazer.foodies.ui.navigation.NavRoutes
import ru.mazer.foodies.ui.screens.catalog.components.FilterRow
import ru.mazer.foodies.ui.screens.common.DishCard
import ru.mazer.foodies.ui.screens.common.FixedButton
import ru.mazer.foodies.ui.theme.Typography
import ru.mazer.foodies.viewmodels.MainViewModel

/**
 * Screen of catalog of products*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogScreen(
    navController: NavController,
    vm: MainViewModel
) {

    //Count of columns in catalog. Could be adaptive
    val columnsCount = 2
    //Current cart items
    val cart = vm.cartList.observeAsState()
    //State for bottom sheet with filters
    val bottomSheetState =
        rememberStandardBottomSheetState(initialValue = SheetValue.Hidden, skipHiddenState = false)
    //State for scaffold of bottom sheet with filters
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    //Scroll state of lazy grid
    val lazyGridScrollState = rememberLazyGridState()
    //Boolean state of lazyGridScrollState. True if grid is scrolled
    val isScrolled =
        remember { derivedStateOf { lazyGridScrollState.firstVisibleItemScrollOffset > 0 } }
    //Exist dishes
    val dishes = vm.filteredDishList.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    //Boolean state that checks if cart is not empty. True if cart is not empty
    val isCartNotEmpty =
        remember { derivedStateOf { cart.value != null && cart.value!!.isNotEmpty() } }
    //Exist tags
    val tags = vm.filtersList.observeAsState()
    //Tags that tagged by user
    val checkedTags = vm.checkedTagsList.observeAsState()
    //Tag for discount is not inside tags.json, so it uses different state value
    val discountTag = vm.discountOnly.observeAsState()
    //Checks if "Острое" tag is exists
    val isHotTagExists =
        remember { derivedStateOf { tags.value?.firstOrNull { it.name == "Острое" } != null } }
    //Checks if "Вегетарианское блюдо" tag is exists
    val isVegaTagExists =
        remember { derivedStateOf { tags.value?.firstOrNull { it.name == "Вегетарианское блюдо" } != null } }
    //Exist categories
    val categories = vm.categories.observeAsState(listOf())
    //Current cart price
    val currentPrice = vm.currentPrice.observeAsState(0)
    //Category that tapped by user
    val currentCategory = vm.currentCategory.observeAsState(categories.value.first().id)

    BottomSheetScaffold(
        topBar = {
            TopLine(
                categories = categories.value,
                currentCategory = currentCategory.value,
                isScrolled = isScrolled.value,
                onFilterClick = {
                    coroutineScope.launch {
                        if (!bottomSheetState.isVisible)
                            bottomSheetState.expand()
                    }
                },
                onSearchClick = {
                    navController.navigate(NavRoutes.Search.route)
                },
                onCategoryClick = {
                    vm.onCategoryClick(it)
                },
                badgeValue = if (checkedTags.value != null) {
                    if (discountTag.value == true)
                        checkedTags.value!!.size + 1
                    else
                        checkedTags.value!!.size
                } else 0
            )
        },
        containerColor = Color.White,
        sheetContainerColor = Color.Transparent,
        sheetSwipeEnabled = false,
        sheetShape = RectangleShape,
        sheetPeekHeight = 0.dp,
        scaffoldState = bottomSheetScaffoldState,
        sheetDragHandle = {},
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Black.copy(alpha = .6f)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = true)
                        .clickable {
                            coroutineScope.launch {
                                vm.onFiltersChanged()
                                bottomSheetState.hide()
                            }
                        }
                )
                Column(
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                        )
                ) {
                    Text(
                        text = "Подобрать блюда",
                        style = Typography.titleMedium,
                        modifier = Modifier
                            .padding(top = 32.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth()
                    )
                    LazyColumn(
                        modifier = Modifier
                            .height(200.dp)
                            .padding(vertical = 16.dp, horizontal = 24.dp)
                    ) {
                        if (tags.value != null)
                            items(tags.value!!) {
                                Box {
                                    FilterRow(
                                        title = it.name,
                                        checked = checkedTags.value!!.contains(it.id),
                                        onCheckedChange = { newValue ->
                                            vm.checkTag(
                                                newValue = newValue,
                                                tagId = it.id
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.BottomCenter)
                                    )
                                }
                            }
                        item {
                            FilterRow(
                                title = "Со скидкой",
                                checked = if (discountTag.value != null) discountTag.value!! else false,
                                onCheckedChange = { vm.checkDiscountTag(it) },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    FixedButton(
                        onClick = {
                            coroutineScope.launch {
                                vm.onFiltersChanged()
                                bottomSheetState.hide()
                            }
                        }
                    ) {
                        Text(text = "Готово")
                    }
                }
            }
        }
    ) { paddingValues ->
        if (!dishes.value.isNullOrEmpty() && dishes.value!!.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    state = lazyGridScrollState,
                    columns = GridCells.Fixed(count = columnsCount),
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(bottom = if (isCartNotEmpty.value) 70.dp else 0.dp)
                ) {
                    items(dishes.value!!) { dish ->
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
                if (isCartNotEmpty.value) {
                    FixedButton(
                        onClick = { navController.navigate(NavRoutes.Cart.route) },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .shadow(
                                elevation = 24.dp,
                                shape = RectangleShape
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cart),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "${currentPrice.value / 100} \u20BD",
                            style = Typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding()
            ) {
                Text(
                    text = "Таких блюд нет :(\nПопробуйте изменить фильтры",
                    modifier = Modifier
                        .alpha(.6f)
                        .align(Alignment.Center)
                )
                if (isCartNotEmpty.value) {
                    FixedButton(
                        onClick = { navController.navigate(NavRoutes.Cart.route) },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .shadow(
                                elevation = 24.dp,
                                shape = RectangleShape
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cart),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "${currentPrice.value / 100} \u20BD",
                            style = Typography.titleMedium,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}