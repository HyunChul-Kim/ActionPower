package com.example.source.local

import com.example.database.dao.DrinkResourceDao
import com.example.database.model.DrinkResourceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteDrinkDataSource @Inject constructor(
    private val drinkResourceDao: DrinkResourceDao
) {

    fun getDrinkResources(): Flow<List<DrinkResourceEntity>> =
        drinkResourceDao.getDrinkResources()

    suspend fun insertDrinkResource(
        drinkResource: DrinkResourceEntity
    ) {
        drinkResourceDao.insertDrinkResource(drinkResource)
    }

    suspend fun deleteDrinkResource(
        id: String
    ) {
        drinkResourceDao.deleteDrinkResource(id)
    }
}