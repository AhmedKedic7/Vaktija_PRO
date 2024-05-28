package com.example.vaktijapro.model.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.vaktijapro.model.models.Prayer

@Dao
interface PrayerDao {
    @Insert
    suspend fun insert(prayer: Prayer)

    @Query("SELECT * FROM prayers ORDER BY prayer_id DESC LIMIT 5")
    suspend fun getLastFivePrayers(): List<Prayer>




}