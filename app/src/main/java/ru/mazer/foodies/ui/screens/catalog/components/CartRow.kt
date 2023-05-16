package ru.mazer.foodies.ui.screens.catalog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ru.mazer.foodies.R
import ru.mazer.foodies.domain.models.Dish
import ru.mazer.foodies.ui.theme.Typography

@Composable
fun CartRow(
    dish: Dish,
    inCartCount: Int,
    modifier: Modifier = Modifier,
    onAdd: (Dish) -> Unit,
    onRemove: (Dish) -> Unit
) {
    Row(
        modifier = modifier
            .shadow(elevation = 1.dp)
            .background(
                color = Color.White,
                shape = RectangleShape
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.img1),
            contentDescription = dish.name,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(96.dp)
                .padding(16.dp)
        )
        Column(
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, end = 16.dp)
        ) {
            Text(text = dish.name)
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
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(1.dp),
                    modifier = Modifier
                        .size(44.dp)
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
                    style = Typography.titleSmall,
                    modifier = Modifier.weight(1f)
                )

                Button(
                    onClick = { onAdd(dish) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(1.dp),
                    modifier = Modifier
                        .size(44.dp)
                        .weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = "Add dish",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                Column(
                    modifier = Modifier.weight(weight = 2f, fill = true),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "${dish.price_current / 100} \u20BD",
                        textAlign = TextAlign.End,
                        style = Typography.bodyLarge
                    )
                    if (dish.price_old != null)
                        Text(
                            text = "${dish.price_old / 100} \u20BD",
                            textDecoration = TextDecoration.LineThrough,
                            textAlign = TextAlign.End,
                            style = Typography.bodyMedium,
                            modifier = Modifier.alpha(.6f)
                        )
                }
            }
        }
    }
}