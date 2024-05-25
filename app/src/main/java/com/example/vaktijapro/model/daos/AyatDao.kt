package com.example.vaktijapro.model.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vaktijapro.model.models.Ayat
import kotlinx.coroutines.flow.Flow

@Dao
interface AyatDao {
    @Query("SELECT * FROM ayats ORDER BY RANDOM() LIMIT 1")
    fun getRandomAyat(): Flow<Ayat>
    /*@Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAyat(ayat: Ayat)*/
}