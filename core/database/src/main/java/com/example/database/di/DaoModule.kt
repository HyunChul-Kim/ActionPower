package com.example.database.di

import com.example.database.ActionPowerDatabase
import com.example.database.dao.DrinkResourceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideDrinkResourceDao(
        database: ActionPowerDatabase
    ): DrinkResourceDao = database.drinkResourceDao()
}