package com.example.vaktijapro.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vaktijapro.ui.theme.VaktijaPROTheme
import com.example.vaktijapro.viewModel.AppViewModelProvider
import com.example.vaktijapro.viewModel.AyatViewModel
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaktijapro.R
import com.example.vaktijapro.model.models.Ayat
import com.example.vaktijapro.ui.screen.navigation.NavigationDestination



object AyatScreenDestination: NavigationDestination {
    override val route: String = "ayat_screen"
    override val title: String = "Ayat Screen"
}


@Composable
fun AyatScreen(
    userId:Int,
    viewModel: AyatViewModel= viewModel(factory=AppViewModelProvider.ayatFactory),
    navigateToAyatScreen: () -> Unit,
    navigateToPrayers: () -> Unit,
    navigateToTutorial: (Int) -> Unit
){




    // Collect the random Ayat from the ViewModel
    val ayat by viewModel.fetchRandomAyat().collectAsState(initial = null)

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xDF005930)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.moon_star),
                contentDescription = "Logo",
                modifier = Modifier.size(100.dp)
            )
            ayat?.let { ayat ->
                Text(
                    text = ayat.ayat_info,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = ayat.ayat,
                    fontSize = 32.sp,
                    color = Color.White,
                    modifier = Modifier.padding(24.dp),

                    textAlign= TextAlign.End
                )

            } ?: Text(
                text = "Loading...",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
            
            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))
            
            Button(
                onClick = { navigateToAyatScreen() },
                 colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.width(300.dp)) {
                Text(text = "Generate new Ayat",
                    fontSize = 20.sp,
                    color = Color(0xDF00502B),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp))
            }

            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))

            Button(
                onClick = { navigateToPrayers() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.width(300.dp)
            ) {
                Text(text = "See Prayer Times",
                    fontSize = 20.sp,
                    color = Color(0xDF00502B),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp))
            }
            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))
            Button(
                onClick = { navigateToTutorial(userId) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.width(300.dp)
            ) {
                Text(text = "Log Prayers",
                    fontSize = 20.sp,
                    color = Color(0xDF00502B),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp))
            }
        }
    }
}


@Preview( showBackground = false )
@Composable
fun AyatScreenPreview(){
    VaktijaPROTheme {
        //AyatScreen()
    }
}