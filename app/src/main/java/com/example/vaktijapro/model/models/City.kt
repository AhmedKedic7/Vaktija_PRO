package com.example.vaktijapro.model.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey(autoGenerate = true)
    val cityId: Int,
    val cityName: String,
    val dawnTime: String,
    val sunriseTime: String,
    val dhuhrTime: String,
    val asrTime: String,
    val maghribTime: String,
    val ishaTime: String
)