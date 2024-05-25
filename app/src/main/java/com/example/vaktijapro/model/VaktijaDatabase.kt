package com.example.vaktijapro.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.vaktijapro.model.daos.AyatDao
import com.example.vaktijapro.model.daos.UserDao
import com.example.vaktijapro.model.models.Ayat
import com.example.vaktijapro.model.models.User

@Database(
    entities = [User::class, Ayat::class], // Include the new entity Ayat
    version = 2, // Increase the version number
    exportSchema = false
)
abstract class VaktijaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun ayatDao(): AyatDao // Add abstract function for AyatDao

    companion object {
        @Volatile
        private var Instance: VaktijaDatabase? = null

        fun getDatabase(context: Context): VaktijaDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext, VaktijaDatabase::class.java,
                    "VaktijaAPPDatabase"
                )
                    .addMigrations(MIGRATION_1_2) // Add migration from version 1 to version 2
                    .build().also { Instance = it }
            }
        }

        // Define your migration here
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL("CREATE TABLE IF NOT EXISTS `ayats` (`ayat_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `ayat_info` TEXT NOT NULL, `ayat` TEXT NOT NULL)")
            }
        }
    }
}
