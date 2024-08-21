package com.example.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoriteToggleButton(
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