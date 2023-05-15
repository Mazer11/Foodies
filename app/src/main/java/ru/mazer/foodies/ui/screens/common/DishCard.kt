package ru.mazer.foodies.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mazer.foodies.R
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.ui.theme.FoodiesTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishCard(
    dish: Dish,
    modifier: Modifier = Modifier,
    inCartCount: Int = 0,
    onAdd: (Dish) -> Unit = {},
    onRemove: (Dish) -> Unit = {},
    onCardClick: () -> Unit = {}
) {

    Card(
        onClick = { onCardClick() },
        modifier = modifier.width(170.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {

        Box {
            Image(
                painter = painterResource(id = R.drawable.img1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )

            if (dish.price_old != null)
                Image(
                    painter = painterResource(id = R.drawable.ic_sales),
                    contentDescription = "Discount",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 8.dp, top = 8.dp)
                        .size(24.dp)
                )
        }
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Text(
                    text = dish.name,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "${dish.measure} ${dish.measure_unit}",
                    modifier = Modifier.alpha(.6f)
                )
            }

            if (inCartCount > 0)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { onRemove(dish) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 2.dp
                        ),
                        contentPadding = PaddingValues(1.dp),
                        modifier = Modifier
                            .size(40.dp)
                            .weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = "Add dish",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    Text(
                        text = inCartCount.toString(),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1.8f)
                    )

                    Button(
                        onClick = { onAdd(dish) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 2.dp
                        ),
                        contentPadding = PaddingValues(1.dp),
                        modifier = Modifier
                            .size(40.dp)
                            .weight(1f)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "Add dish",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            else
                Button(
                    onClick = { onAdd(dish) },
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    shape = RoundedCornerShape(8.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Text(text = "${dish.price_current / 100} \u20BD")
                    if (dish.price_old != null)
                        Text(
                            text = "${dish.price_old / 100} \u20BD",
                            textDecoration = TextDecoration.LineThrough,
                            modifier = Modifier.alpha(.6f)
                        )
                }
        }
    }

}

@Preview
@Composable
fun CardPreview() {
    FoodiesTheme {

        Row {
            DishCard(
                inCartCount = 0,
                dish = Dish(
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
                )
            )
            Divider(modifier = Modifier.width(8.dp))
            DishCard(
                inCartCount = 1,
                dish = Dish(
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
                )
            )
        }
    }
}

























