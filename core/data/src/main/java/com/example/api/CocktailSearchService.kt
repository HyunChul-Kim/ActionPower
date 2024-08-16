package com.example.api

import com.example.model.CocktailSearchResponse
import retrofit2.http.GET

interface CocktailSearchService {
    @GET("/v1/1/filter.php?a=Alcoholic")
    suspend fun getContainAlcoholicCocktailList(): CocktailSearchResponse
}