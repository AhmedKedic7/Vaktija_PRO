package com.example.vaktijapro

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.vaktijapro.ui.screen.AyatScreen
import com.example.vaktijapro.ui.screen.City
import com.example.vaktijapro.ui.screen.LoginScreen
import com.example.vaktijapro.ui.screen.Prayers
import com.example.vaktijapro.ui.screen.Register
import com.example.vaktijapro.ui.screen.Display
import com.example.vaktijapro.ui.screen.navigation.AppNavHost
import com.example.vaktijapro.ui.theme.VaktijaPROTheme

class MainActivity : ComponentActivity() {
    val cities = listOf<City>(
        City("Sarajevo"),
        City("Banja Luka"),
        City("Bihać"),
        City("Cazin"),
        City("Bužim"),
        City("Sanski Most"),
        City("Bijeljina"),
        City("Bos. Krupa"),
        City("Bos. Petrovac"),
        City("Bos. Novi"),
        City("Kiseljak"),
        City("Tuzla"),
        City("Ključ"),
        City("Mostar"),
        City("Doboj"),
        City("Bos. Brod"),
        City("Olovo"),
        City("Zvornik"),
        City("Srebrenica"),
        City("Tešanj"),
        City("Vitez"),
        City("V. Kladuša"),
        City("Prijedor"),
        City("Travnik"),
        City("Maglaj"),
        City("Zenica"),
        City("Konjic"),
        City("Visoko"),
        City("Kakanj"),
    )
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VaktijaPROTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     //Display();
                    //Register()
                    //LoginScreen()
                    //AyatScreen()
                    //Prayers();
                    //ListOfLocations(cities = cities)
                    AppNavHost(navController = rememberNavController())
                }
            }
        }
    }
}



