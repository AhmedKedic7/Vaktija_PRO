package com.example.vaktijapro.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vaktijapro.model.models.User
import com.example.vaktijapro.model.repositories.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginRegistrationViewModel(private val userRepository: UserRepository) : ViewModel() {
    var userUiState by mutableStateOf(UserUiState())
        private set

    private val _userId = MutableLiveData<Int>()
    val userId: LiveData<Int> get() = _userId

    fun register() {
        viewModelScope.launch {
            if (validateInput()) {
                userRepository.insert(userUiState.userDetails.toUser())
            }
        }
    }
    fun login(email:String,password: String, onSuccess:(Int)->Unit, onFailure:()->Unit){
        viewModelScope.launch{
            val user = userRepository.getUserEmail(email).first()
            if (user != null && user.password == password) {
                _userId.value = user.id
                onSuccess(user.id)
            } else {
                onFailure()
            }
        }

    }

    private suspend fun validateInput(): Boolean {
        return with(userUiState.userDetails) {
            username.isNotBlank() && email.isNotBlank() && password.isNotBlank() && checkEmail()
        }
    }

    private suspend fun checkEmail(): Boolean {
        val existingUser = userRepository.getUserEmail(userUiState.userDetails.email).first()
        return existingUser == null
    }

    fun updateUiState(userDetails: UserDetails) {
        val isEntryValid = userDetails.username.isNotBlank() && userDetails.email.isNotBlank() && userDetails.password.isNotBlank()
        userUiState = UserUiState(userDetails = userDetails, isEntryValid = isEntryValid)
    }
}