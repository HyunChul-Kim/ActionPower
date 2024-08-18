package com.example.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.search.component.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchQuery: String = "",
    searchResultUiState: SearchResultUiState = SearchResultUiState.Loading,
    onSearchQueryChanged: (String) -> Unit
) {
    Column(modifier = modifier) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        SearchBar(
            query = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged
        )
        when(searchResultUiState) {
            SearchResultUiState.Loading -> {
                Log.d("#chul", "SearchResultUiState is Loading")
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator()
                }
            }
            SearchResultUiState.Error -> {
                Log.d("#chul", "SearchResultUiState is Error")
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Retry")
                }
            }
            is SearchResultUiState.Success -> {
                Log.d("#chul", "SearchResultUiState is Success")
            }
        }
    }
}