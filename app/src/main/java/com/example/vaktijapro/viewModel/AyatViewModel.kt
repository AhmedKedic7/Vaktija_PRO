package com.example.vaktijapro.viewModel;

import androidx.lifecycle.ViewModel
import com.example.vaktijapro.model.models.Ayat
import com.example.vaktijapro.model.repositories.AyatRepository
import kotlinx.coroutines.flow.Flow


class AyatViewModel(private val ayatRepository: AyatRepository) : ViewModel() {
    /*suspend fun insertAyatIntoDatabase(ayat: Ayat) {
        ayatRepository.insertAyatIntoDatabase(ayat)
    }*/


    fun fetchRandomAyat(): Flow<Ayat> = ayatRepository.getRandomAyat()
}
