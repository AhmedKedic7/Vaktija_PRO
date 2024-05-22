package com.example.vaktijapro.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vaktijapro.model.daos.UserDao
import com.example.vaktijapro.model.models.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class VaktijaDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: VaktijaDatabase? = null
        fun getDatabase(context: Context): VaktijaDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context, VaktijaDatabase::class.java,
                    "VaktijaAPPDatabase"
                )
                    .build().also { Instance = it }
            }
        }
    }
}