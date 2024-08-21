package com.example.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun DetailScreen(
    modifier: Modifier = Modifier,
    drinkId: String,
    viewModel: DetailViewModel
) {

    Column(
        modifier = modifier
    ) {
        Text(text = "drink id is $drinkId")
        Text(text = "title")
        Text(text = "description")
    }
}