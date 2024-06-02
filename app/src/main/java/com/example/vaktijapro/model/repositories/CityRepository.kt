package com.example.vaktijapro.model.repositories

import com.example.vaktijapro.model.daos.CityDao
import com.example.vaktijapro.model.models.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class CityRepository(private val cityDao: CityDao): BaseRepository<City> {
    override suspend fun insert(t: City) = cityDao.insert(t)
    override suspend fun update(t: City) = cityDao.update(t)
    override suspend fun delete(t: City) = cityDao.delete(t)
    fun getCitiesOrderedByName(): Flow<List<City>> = cityDao.getCitiesOrderedByName()
    suspend fun getCityById(cityId: Int): City? {
        return withContext(Dispatchers.IO) {
            cityDao.getCityById(cityId)
        }
    }

    suspend fun addCity(city: City) {
        cityDao.insert(city)
    }
}