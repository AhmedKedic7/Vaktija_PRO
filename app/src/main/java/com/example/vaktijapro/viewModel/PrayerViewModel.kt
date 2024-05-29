package com.example.vaktijapro.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vaktijapro.model.models.Prayer
import com.example.vaktijapro.model.repositories.PrayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PrayerViewModel (private val prayerRepository: PrayerRepository):ViewModel(){
    val lastFivePrayers: Flow<List<Prayer>> = prayerRepository.getLastFivePrayers()


    fun logPrayer(prayerName: String) {
        viewModelScope.launch {
            prayerRepository.insertPrayer(prayerName)
        }
    }
}