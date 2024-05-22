package com.example.vaktijapro.model

import android.content.Context
import com.example.vaktijapro.model.repositories.UserRepository

interface AppContainer {
    val userRepository: UserRepository
}
class AppDataContainer(private val context: Context): AppContainer {

    override val userRepository: UserRepository by lazy {
        UserRepository(VaktijaDatabase.getDatabase(context).userDao())
    }
}