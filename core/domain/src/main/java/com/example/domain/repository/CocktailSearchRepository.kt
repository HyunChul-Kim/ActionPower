package com.example.domain.repository

import com.example.model.SearchResult
import com.example.domain.model.ApiResult
import com.example.model.DrinkDetailResource
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

    fun getCocktailDetailById(
        id: String
    ): Flow<ApiResult<DrinkDetailResource>>
}