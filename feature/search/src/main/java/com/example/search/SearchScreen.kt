package com.example.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.app.core.model.DrinkResource
import com.example.search.component.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    searchResultUiState: SearchResultUiState = SearchResultUiState.Loading,
    onSearchQueryChanged: (String) -> Unit
) {
    val listState = rememberLazyListState()

    Column(modifier = modifier) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        SearchBar(
            query = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged
        )
        when(searchResultUiState) {
            SearchResultUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            SearchResultUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Something wrong")
                }
            }
            is SearchResultUiState.Success -> {
                SearchResultColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    listState = listState,
                    drinkList = searchResultUiState.drinks
                )
            }
        }
    }
}

@Composable
private fun SearchResultColumn(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    drinkList: List<DrinkResource>
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = drinkList.size,
            key = { index -> index }
        ) { index ->
            val drinkItem = drinkList[index]
            SearchResultItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                item = drinkItem
            )
        }
    }
}

@Composable
private fun SearchResultItem(
    modifier: Modifier = Modifier,
    item: DrinkResource
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(0.dp),
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color.White)
        ) {
            val imageEnd = maxWidth * 0.3f
            val imageSize = with(LocalDensity.current) {
                val width = imageEnd.toPx().toInt()
                Size(
                    width,
                    width
                )
            }
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.thumbnail)
                    .size(imageSize)
                    .crossfade(true)
                    .build(),
                contentDescription = "drink item thumbnail",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(imageEnd)

            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageEnd)
                    .padding(start = imageEnd)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(text = item.name)
                    Text(text = item.category)
                }
                FavoriteToggleButton(
                    modifier = Modifier
                        .align(Alignment.End),
                    isFavorite = false,
                    onCheckedChange = {}
                )
            }
        }
    }
}

@Composable
private fun FavoriteToggleButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    IconToggleButton(
        modifier = modifier,
        checked = isFavorite,
        onCheckedChange = onCheckedChange
    ) {
        if(isFavorite) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.StarBorder,
                contentDescription = null
            )
        }
    }
}