package com.example.vaktijapro.model.repositories

import com.example.vaktijapro.model.daos.PrayerDao
import com.example.vaktijapro.model.models.Prayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class PrayerRepository (private val prayerDao :PrayerDao){
    suspend fun insertPrayer(prayer: String) {
        val prayer = Prayer(prayer = prayer)
        prayerDao.insert(prayer)
    }
    fun getLastFivePrayers(): Flow<List<Prayer>> = flow {
        val lastFivePrayers = withContext(Dispatchers.IO) {
            prayerDao.getLastFivePrayers()
        }
        emit(lastFivePrayers)
    }
}