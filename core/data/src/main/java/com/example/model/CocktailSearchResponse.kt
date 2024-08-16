package com.example.model

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
    @SerializedName("isDrink")
    val idDrink: String,
    @SerializedName("strCategory")
    val strCategory: String,
)