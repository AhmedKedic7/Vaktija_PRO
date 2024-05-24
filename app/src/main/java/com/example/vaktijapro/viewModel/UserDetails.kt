package com.example.vaktijapro.viewModel

import com.example.vaktijapro.model.models.User

data class UserDetails(
    val id: Int = 0,
    val username: String = "",
    val email: String = "",
    val password: String = ""
)

data class UserUiState(
    val userDetails: UserDetails = UserDetails(),
    val isEntryValid: Boolean = false
)

fun UserDetails.toUser(): User = User(
    id = id,
    username = username,
    email = email,
    password = password
)

fun User.toUserDetails() = UserDetails(
    id = id,
    username = username,
    email = email,
    password = password
)

fun User.toUserUiState(isEntryValid: Boolean = false): UserUiState = UserUiState(
    userDetails = this.toUserDetails(),
    isEntryValid = isEntryValid
)