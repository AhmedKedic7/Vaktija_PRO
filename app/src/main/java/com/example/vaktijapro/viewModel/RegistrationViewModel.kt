package com.example.vaktijapro.viewModel

import androidx.lifecycle.ViewModel
import com.example.vaktijapro.model.daos.UserDao
import com.example.vaktijapro.model.models.User

class RegistrationViewModel (private val userDao: UserDao) : ViewModel() {
    suspend fun registerUser(user: User) {
        userDao.insert(user)
    }
}