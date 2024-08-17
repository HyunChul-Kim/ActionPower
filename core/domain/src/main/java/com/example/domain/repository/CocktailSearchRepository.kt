package com.example.domain.repository

import com.example.app.core.model.SearchResult
import com.example.domain.model.ApiResult
import kotlinx.coroutines.flow.Flow

interface CocktailSearchRepository {
    fun getFilteredCocktailList(
        filter: String,
    ): Flow<ApiResult<SearchResult>>

}