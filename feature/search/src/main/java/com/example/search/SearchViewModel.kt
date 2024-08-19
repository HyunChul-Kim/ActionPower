package com.example.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ApiResult
import com.example.domain.repository.FavoriteDrinkRepository
import com.example.domain.usecase.GetCocktailSearchResult
import com.example.model.UserDrinkResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCocktailSearchResult: GetCocktailSearchResult,
    private val favoriteDrinkRepository: FavoriteDrinkRepository
): ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery get() = _searchQuery.asStateFlow()

    val searchResultUiState: StateFlow<SearchResultUiState> =
        searchQuery.debounce(500)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                getCocktailSearchResult(query).map { apiResult ->
                    Log.d("#chul", "SearchViewModel apiResult")
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
                }.onStart {
                    emit(SearchResultUiState.Loading)
                }
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SearchResultUiState.Loading
            )

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onToggleFavorite(userDrinkResource: UserDrinkResource, isFavorite: Boolean) {
        viewModelScope.launch {
            if(isFavorite) {
                favoriteDrinkRepository.insertDrinkResource(
                    userDrinkResource.drinkResource
                )
            } else {
                favoriteDrinkRepository.deleteDrinkResource(
                    userDrinkResource.drinkResource.id
                )
            }
        }
    }
}