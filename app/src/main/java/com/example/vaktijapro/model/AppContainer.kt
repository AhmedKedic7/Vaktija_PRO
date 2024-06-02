package com.example.vaktijapro.model

import android.content.Context
import com.example.vaktijapro.model.repositories.AyatRepository
import com.example.vaktijapro.model.repositories.CityRepository
import com.example.vaktijapro.model.repositories.PrayerRepository
import com.example.vaktijapro.model.repositories.UserRepository

interface AppContainer {
    val userRepository: UserRepository
    val ayatRepository: AyatRepository
    val prayerRepository: PrayerRepository
    val cityRepository:CityRepository
}
class AppDataContainer(private val context: Context): AppContainer {

    override val userRepository: UserRepository by lazy {
        UserRepository(VaktijaDatabase.getDatabase(context).userDao())
    }
    override val ayatRepository: AyatRepository by lazy{
        AyatRepository(VaktijaDatabase.getDatabase(context).ayatDao())
    }
    override val prayerRepository: PrayerRepository by lazy{
        PrayerRepository(VaktijaDatabase.getDatabase(context).prayerDao())
    }
    override val cityRepository: CityRepository by lazy {
        CityRepository(VaktijaDatabase.getDatabase(context).cityDao())
    }

}