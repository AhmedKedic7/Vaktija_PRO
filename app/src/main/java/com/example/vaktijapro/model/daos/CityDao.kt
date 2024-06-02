package com.example.vaktijapro.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.vaktijapro.model.models.City
import com.example.vaktijapro.model.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Insert
    suspend fun insert(city: City)

    @Update
    suspend fun update(city: City)

    @Delete
    suspend fun delete(city: City)

    @Query("SELECT * FROM cities WHERE cityId = :id")
    fun getCityById(id: Int): City

    @Query("SELECT * FROM cities ORDER BY cityName ASC")
    fun getCitiesOrderedByName(): Flow<List<City>>
}