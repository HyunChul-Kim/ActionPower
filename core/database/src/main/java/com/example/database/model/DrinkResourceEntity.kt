package com.example.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.DrinkResource

@Entity(
    tableName = "drink_resources"
)
data class DrinkResourceEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val category: String,
    val thumbnail: String
)

fun DrinkResourceEntity.toExternalModel() = DrinkResource(
    id = id,
    name = name,
    category = category,
    thumbnail = thumbnail
)