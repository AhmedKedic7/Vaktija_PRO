package com.example.vaktijapro

import android.app.Application
import com.example.vaktijapro.model.AppContainer
import com.example.vaktijapro.model.AppDataContainer

class VaktijaApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}