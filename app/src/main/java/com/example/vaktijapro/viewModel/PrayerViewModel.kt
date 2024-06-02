package com.example.vaktijapro.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vaktijapro.model.models.Prayer
import com.example.vaktijapro.model.repositories.PrayerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PrayerViewModel (private val prayerRepository: PrayerRepository):ViewModel(){
    private var userId: Int = 0

    fun setUserId(id: Int) {
        userId = id
    }

    // Function to get the last five prayers for the current user
    fun lastFivePrayers(userId: Int): Flow<List<Prayer>> {
        return prayerRepository.getLastFivePrayers(userId)
    }

    // Function to log a prayer for the current user
    fun logPrayer(prayer: String, userId: Int) {
        val timestamp = System.currentTimeMillis()
        val prayer = Prayer(prayer = prayer, timestamp = timestamp, userId = userId)
        viewModelScope.launch {
            prayerRepository.insertPrayer(prayer)
        }
    }
    fun deletePrayerById(prayerId: Int) {
        viewModelScope.launch {
            prayerRepository.deletePrayerById(prayerId)
        }
    }


}