package com.example.domain.repository

import com.example.model.SearchResult
import com.example.domain.model.ApiResult
import kotlinx.coroutines.flow.Flow

interface CocktailSearchRepository {
    fun getCocktailListByFilter(
        filter: String,
    ): Flow<ApiResult<com.example.model.SearchResult>>

    fun getCocktailListByFirstLetter(
        letter: String
    ): Flow<ApiResult<com.example.model.SearchResult>>

    fun getCocktailListByName(
        name: String
    ): Flow<ApiResult<com.example.model.SearchResult>>
}