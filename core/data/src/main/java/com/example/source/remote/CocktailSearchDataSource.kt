package com.example.source.remote

import com.example.api.CocktailSearchService
import javax.inject.Inject

class CocktailSearchDataSource @Inject constructor(
    private val service: CocktailSearchService
) {
    suspend fun getFilteredCocktailList(
        filter: String,
    ) = service.getFilteredSearchResponse(filter)
}