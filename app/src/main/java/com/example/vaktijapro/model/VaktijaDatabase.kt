package com.example.vaktijapro.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.vaktijapro.model.daos.AyatDao
import com.example.vaktijapro.model.daos.PrayerDao
import com.example.vaktijapro.model.daos.UserDao
import com.example.vaktijapro.model.models.Ayat
import com.example.vaktijapro.model.models.Prayer
import com.example.vaktijapro.model.models.User

@Database(
    entities = [User::class, Ayat::class,Prayer::class], // Include the new entity Ayat
    version = 5, // Increase the version number
    exportSchema = false
)
abstract class VaktijaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun ayatDao(): AyatDao // Add abstract function for AyatDao
    abstract fun prayerDao(): PrayerDao

    companion object {
        @Volatile
        private var Instance: VaktijaDatabase? = null

        fun getDatabase(context: Context): VaktijaDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext, VaktijaDatabase::class.java,
                    "VaktijaAPPDatabase"
                )
                    //.addMigrations(MIGRATION_3_5) // Add migration from version 1 to version 2
                    .build().also { Instance = it }
            }
        }

        // Define your migration here
        private val MIGRATION_3_5 = object : Migration(3, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("ALTER TABLE prayers RENAME TO prayers_old")


                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS prayers (
                prayer_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                prayer_name TEXT NOT NULL,
                prayer_time TEXT NOT NULL,
                is_completed INTEGER NOT NULL DEFAULT 0
                        
                    )
                """.trimIndent())


                database.execSQL("DROP TABLE prayers_old")
            }
        }
    }
}
