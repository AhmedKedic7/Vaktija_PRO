package com.example.vaktijapro.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


import com.example.vaktijapro.R



import com.example.vaktijapro.VaktijaApplication
import com.example.vaktijapro.ui.screen.navigation.NavigationDestination

import com.example.vaktijapro.ui.theme.VaktijaPROTheme
import com.example.vaktijapro.viewModel.AppViewModelProvider
import com.example.vaktijapro.viewModel.LoginRegistrationViewModel
import com.example.vaktijapro.viewModel.UserDetails
import kotlinx.coroutines.launch

object RegistrationDestination: NavigationDestination {
    override val route: String = "register"
    override val title: String = "Register"
}
@Composable
fun Register(
    viewModel: LoginRegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToLogin: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }

    val coroutineScope = rememberCoroutineScope()

    Surface (modifier = Modifier.fillMaxSize(),
        color = Color(0xDF005930)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.moon_star),
                contentDescription = "",
                modifier = Modifier.size(width = 100.dp, height = 100.dp)
            )

            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.size(width = 0.dp, height = 50.dp))

            TextField(
                value = username,
                onValueChange = {
                    username = it
                    viewModel.updateUiState(UserDetails(username = username, email = email, password = password))
                },
                enabled = true,
                label = {
                    Text(text = "Username")
                },
                isError = false
            )

            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

            TextField(
                value = email,
                onValueChange = {
                    email = it
                    viewModel.updateUiState(UserDetails(username = username, email = email, password = password))
                },
                enabled = true,
                label = {
                    Text(text = "Email")
                },
                isError = false
            )

            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

            TextField(
                value = password,
                onValueChange = {
                    password = it
                    viewModel.updateUiState(UserDetails(username = username, email = email, password = password))
                },
                label = { Text(text = "Password") },
                isError = false,
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            painter = if (showPassword) painterResource(id = R.drawable.baseline_visibility_24) else painterResource(id = R.drawable.baseline_visibility_off_24),
                            contentDescription = if (showPassword) "Hide password" else "Show password"
                        )
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )

            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(text = "Confirm Password") },
                isError = false,
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(
                            painter = if (showPassword) painterResource(id = R.drawable.baseline_visibility_24) else painterResource(id = R.drawable.baseline_visibility_off_24),
                            contentDescription = if (showPassword) "Hide password" else "Show password"
                        )
                    }
                },
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )

            Spacer(modifier = Modifier.size(width = 0.dp, height = 5.dp))

            Text(text = when {
                password == confirmPassword -> ""
                else -> "Passwords do not match!"
            }, color = Color.Red)


            TextButton(
                onClick = { navigateToLogin() },
            ) {
                Text(text = "Already have an account?", color = Color.White)
            }

            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

            Button( onClick = {
                if (password == confirmPassword) {
                    coroutineScope.launch {
                        try {
                            viewModel.register()
                            errorMessage= "Registration successful!"
                        } catch (e: Exception) {
                            errorMessage = e.message.toString()
                        }
                    }
                } else {
                    errorMessage = "Passwords do not match!"
                }
            }, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                Text(
                    text = "Register",
                    fontSize = 20.sp,
                    color = Color(0xDF00502B),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
                )
            }
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Preview( showBackground = false)
@Composable
fun RegisterPreview(){
    VaktijaPROTheme {
        //Register()
    }
}