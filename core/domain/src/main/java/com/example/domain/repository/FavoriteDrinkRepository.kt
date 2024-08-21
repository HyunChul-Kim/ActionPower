package com.example.domain.repository

import com.example.model.DrinkDetailResource
import com.example.model.DrinkResource
import kotlinx.coroutines.flow.Flow

interface FavoriteDrinkRepository {

    fun getDrinkResources(): Flow<List<DrinkResource>>

    suspend fun insertDrinkResource(drinkResource: DrinkResource)

    suspend fun insertDrinkResource(drinkDetailResource: DrinkDetailResource)

    suspend fun deleteDrinkResource(id: String)
}