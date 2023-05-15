package ru.mazer.foodies.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.mazer.foodies.R
import ru.mazer.foodies.domain.models.Tag

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLine(
    tags: List<Tag>,
    onFilterClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onTagClick: () -> Unit = {}
) {

    val selectedId = remember { mutableStateOf(1) }

    Column {
        CenterAlignedTopAppBar(
            title = {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Foodies logo",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(width = 111.dp, height = 44.dp)
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    onFilterClick()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "Filters",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    onSearchClick()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search dish",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
        )

        LazyRow(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            items(tags) { tag ->
                Text(
                    text = tag.name,
                    color = if (tag.id == selectedId.value) Color.White else Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clickable {
                            selectedId.value = tag.id
                            onTagClick()
                        }
                        .background(
                            color = if (tag.id == selectedId.value)
                                MaterialTheme.colorScheme.primary
                            else
                                Color.Transparent,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
        }
    }
}