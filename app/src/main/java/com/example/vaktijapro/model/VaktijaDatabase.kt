package com.example.vaktijapro.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.vaktijapro.model.daos.AyatDao
import com.example.vaktijapro.model.daos.CityDao
import com.example.vaktijapro.model.daos.PrayerDao
import com.example.vaktijapro.model.daos.UserDao
import com.example.vaktijapro.model.models.Ayat
import com.example.vaktijapro.model.models.City
import com.example.vaktijapro.model.models.Prayer
import com.example.vaktijapro.model.models.User

@Database(
    entities = [User::class, Ayat::class,Prayer::class, City::class], // Include the new entity Ayat
    version = 8, // Increase the version number
    exportSchema = false
)
abstract class VaktijaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun ayatDao(): AyatDao
    abstract fun prayerDao(): PrayerDao
    abstract fun cityDao(): CityDao

    companion object {
        @Volatile
        private var Instance: VaktijaDatabase? = null

        fun getDatabase(context: Context): VaktijaDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext, VaktijaDatabase::class.java,
                    "VaktijaAPPDatabase"
                )
                    //.addMigrations(MIGRATION_7_8) // Add migration from version 1 to version 2
                    .build().also { Instance = it }
            }
        }

        // Define your migration here
        private val MIGRATION_7_8 = object : Migration(7, 8) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS cities (
                        cityId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        cityName TEXT NOT NULL,
                        dawnTime TEXT NOT NULL,
                        sunriseTime TEXT NOT NULL,
                        dhuhrTime TEXT NOT NULL,
                        asrTime TEXT NOT NULL,
                        maghribTime TEXT NOT NULL,
                        ishaTime TEXT NOT NULL
                    )
                """.trimIndent()
                )
            }
        }
    }
}
