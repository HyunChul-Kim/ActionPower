package com.example.domain.repository

import com.example.app.core.model.SearchResult
import com.example.domain.model.ApiResult
import kotlinx.coroutines.flow.Flow

interface CocktailSearchRepository {
    fun getCocktailListByFilter(
        filter: String,
    ): Flow<ApiResult<SearchResult>>

    fun getCocktailListByFirstLetter(
        letter: String
    ): Flow<ApiResult<SearchResult>>

    fun getCocktailListByName(
        name: String
    ): Flow<ApiResult<SearchResult>>
}