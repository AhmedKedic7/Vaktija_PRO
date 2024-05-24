package com.example.vaktijapro.viewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.vaktijapro.VaktijaApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            LoginRegistrationViewModel(
                userApplication().container.userRepository
            )
        }
    }
}

fun CreationExtras.userApplication(): VaktijaApplication = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as VaktijaApplication