package com.example.repository

import com.example.database.model.DrinkResourceEntity
import com.example.database.model.toExternalModel
import com.example.model.DrinkResource
import com.example.domain.repository.FavoriteDrinkRepository
import com.example.model.DrinkDetailResource
import com.example.source.local.FavoriteDrinkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteDrinkRepositoryImpl @Inject constructor(
    private val favoriteDrinkDataSource: FavoriteDrinkDataSource
): FavoriteDrinkRepository {
    override fun getDrinkResources(): Flow<List<DrinkResource>> {
        return favoriteDrinkDataSource.getDrinkResources().map { drinkResources ->
            drinkResources.map { it.toExternalModel() }
        }
    }

    override suspend fun insertDrinkResource(drinkResource: DrinkResource) {
        favoriteDrinkDataSource.insertDrinkResource(
            DrinkResourceEntity(
                id = drinkResource.id,
                name = drinkResource.name,
                thumbnail = drinkResource.thumbnail,
                category = drinkResource.category
            )
        )
    }

    override suspend fun insertDrinkResource(drinkDetailResource: DrinkDetailResource) {
        favoriteDrinkDataSource.insertDrinkResource(
            DrinkResourceEntity(
                id = drinkDetailResource.id,
                name = drinkDetailResource.name,
                thumbnail = drinkDetailResource.thumbnail,
                category = drinkDetailResource.category
            )
        )
    }

    override suspend fun deleteDrinkResource(id: String) {
        favoriteDrinkDataSource.deleteDrinkResource(id)
    }
}