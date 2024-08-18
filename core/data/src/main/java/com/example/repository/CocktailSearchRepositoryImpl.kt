package com.example.repository

import com.example.app.core.model.SearchResult
import com.example.domain.model.ApiResult
import com.example.domain.repository.CocktailSearchRepository
import com.example.model.toDomain
import com.example.source.remote.CocktailSearchDataSource
import com.example.util.safeFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CocktailSearchRepositoryImpl @Inject constructor(
    private val cocktailSearchDataSource: CocktailSearchDataSource,
): CocktailSearchRepository {

    override fun getCocktailListByFilter(
        filter: String
    ): Flow<ApiResult<SearchResult>> =
        safeFlow {
            cocktailSearchDataSource.getCocktailListByFilter(filter).toDomain()
        }

    override fun getCocktailListByFirstLetter(
        letter: String
    ): Flow<ApiResult<SearchResult>> =
        safeFlow {
            cocktailSearchDataSource.getCocktailListByFirstLetter(letter).toDomain()
        }

    override fun getCocktailListByName(
        name: String
    ): Flow<ApiResult<SearchResult>> =
        safeFlow {
            cocktailSearchDataSource.getCocktailListByName(name).toDomain()
        }
}