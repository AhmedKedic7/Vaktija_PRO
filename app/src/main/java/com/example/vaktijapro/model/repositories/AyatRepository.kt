package com.example.vaktijapro.model.repositories

import com.example.vaktijapro.model.daos.AyatDao
import com.example.vaktijapro.model.models.Ayat
import kotlinx.coroutines.flow.Flow

class AyatRepository(private val ayatDao: AyatDao) {



    fun getRandomAyat(): Flow<Ayat> = ayatDao.getRandomAyat()

}