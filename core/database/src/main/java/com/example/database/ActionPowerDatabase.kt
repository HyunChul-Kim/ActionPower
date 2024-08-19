package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.DrinkResourceDao
import com.example.database.model.DrinkResourceEntity

@Database(
    entities = [
        DrinkResourceEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class ActionPowerDatabase: RoomDatabase() {
    abstract fun drinkResourceDao(): DrinkResourceDao
}