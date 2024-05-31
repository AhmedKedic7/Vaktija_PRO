package com.example.vaktijapro.model.daos

import androidx.room.Dao

import androidx.room.Insert
import androidx.room.Query

import com.example.vaktijapro.model.models.Prayer
import kotlinx.coroutines.flow.Flow

@Dao
interface PrayerDao {
    @Insert
    suspend fun insertPrayer(prayer: Prayer)

    @Query("SELECT * FROM prayers WHERE user_id = :userId ORDER BY prayer_id DESC LIMIT 5")
    fun getLastFivePrayers(userId: Int): Flow<List<Prayer>>

}