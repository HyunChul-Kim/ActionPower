package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.model.DrinkResourceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkResourceDao {

    @Query("SELECT * FROM drink_resources")
    fun getDrinkResources(): Flow<List<DrinkResourceEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDrinkResource(
        drinkResource: DrinkResourceEntity
    )

    @Query("DELETE FROM drink_resources WHERE id = :id")
    suspend fun deleteDrinkResource(
        id: String
    )
}