package com.example.source.remote

import com.example.api.CocktailSearchService
import javax.inject.Inject

class CocktailSearchDataSource @Inject constructor(
    private val service: CocktailSearchService
) {
    suspend fun getCocktailListByFilter(
        filter: String,
    ) = service.getSearchByFilterResponse(filter)

    suspend fun getCocktailListByFirstLetter(
        letter: String
    ) = service.getSearchByFirstLetterResponse(letter)

    suspend fun getCocktailListByName(
        name: String
    ) = service.getSearchByNameResponse(name)

    suspend fun getCocktailDetailById(
        id: String
    ) = service.getCocktailDetailsById(id)
}