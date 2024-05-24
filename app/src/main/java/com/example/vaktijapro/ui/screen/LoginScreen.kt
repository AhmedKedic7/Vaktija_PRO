package com.example.vaktijapro.ui.screen


import android.content.res.Configuration
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vaktijapro.R
import com.example.vaktijapro.ui.theme.VaktijaPROTheme
import com.example.vaktijapro.viewModel.AppViewModelProvider
import com.example.vaktijapro.viewModel.LoginRegistrationViewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun LoginScreen( viewModel: LoginRegistrationViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }

    Surface (modifier = Modifier.fillMaxSize(),
    color = Color(0xDF005930)) {


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

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        TextField(
            value = email,
            onValueChange = {
                email = it
                viewModel.updateUiState(viewModel.userUiState.userDetails.copy(email = it))
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
            onValueChange = { password = it
                            viewModel.updateUiState(viewModel.userUiState.userDetails.copy(password=it))},
            label = {
                Text(text = "Password")
            },
            isError = false,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_visibility_off_24),
                    contentDescription = ""
                )
            },
            visualTransformation =  PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        )

        Spacer(modifier = Modifier.size(width = 0.dp, height = 5.dp))

        TextButton(
            onClick = { /*TODO*/ },
        ) {
            Text(text = "Do not have an account?", color = Color.White)
        }

        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

        Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
            Text(
                text = "Login",
                fontSize = 20.sp,
                color = Color(0xDF00502B),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
            )
        }
    }
    }
}

@Preview( showBackground = false )
@Composable
fun LoginScreenPreview(){
    VaktijaPROTheme {
        LoginScreen()
    }
}