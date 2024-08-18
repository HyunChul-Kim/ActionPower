package com.example.model

import com.example.app.core.model.DrinkResource
import com.example.app.core.model.SearchResult
import com.google.gson.annotations.SerializedName

data class CocktailSearchResponse(
    @SerializedName("drinks")
    val drinks: List<CocktailSearchDrink>,
)

data class CocktailSearchDrink(
    @SerializedName("strDrink")
    val strDrink: String,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String,
    @SerializedName("idDrink")
    val idDrink: String,
    @SerializedName("strCategory")
    val strCategory: String?,
)

fun CocktailSearchResponse.toDomain() = SearchResult(
    drinkResources = drinks.map { item ->
        DrinkResource(
            name = item.strDrink,
            thumbnail = item.strDrinkThumb,
            id = item.idDrink,
            category = item.strCategory ?: "",
        )
    }
)