package com.example.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiResult
import com.example.domain.usecase.GetCocktailSearchResult
import com.example.domain.usecase.GetFilteredCocktailList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCocktailSearchResult: GetCocktailSearchResult
): ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery get() = _searchQuery.asStateFlow()

    val searchResultUiState: StateFlow<SearchResultUiState> =
        searchQuery.debounce(500)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                Log.d("#chul", query)
            getCocktailSearchResult(query).map { apiResult ->
                when(apiResult) {
                    is ApiResult.Success -> {
                        SearchResultUiState.Success(
                            drinks = apiResult.value.drinkResources
                        )
                    }
                    else -> {
                        SearchResultUiState.Error
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SearchResultUiState.Loading,
        )

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}