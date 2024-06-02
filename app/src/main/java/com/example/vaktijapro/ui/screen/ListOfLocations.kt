package com.example.vaktijapro.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaktijapro.R
import com.example.vaktijapro.ui.screen.navigation.NavigationDestination
import com.example.vaktijapro.ui.theme.VaktijaPROTheme

data class City(val name:String);

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

val backgroundColor = 0xFFE4E4E4;

object ListOfCities: NavigationDestination {
    override val route: String = "cities"
    override val title: String = "Cities"
}

@Composable
fun ListOfLocations(
    navigateToPrayers: () -> Unit
)  {
    var selectedCity by remember { mutableStateOf<String?>(null) }

    Surface(
        color = Color(0xFFE4E4E4)
    ) {
        Column (

        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Spacer(modifier = Modifier.size(width = 0.dp, height = 4.5.dp))
                    Button(
                        onClick = { navigateToPrayers() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(backgroundColor))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.left_arrow),
                            contentDescription = "left arrow",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
                Spacer(modifier = Modifier.size(width = 10.dp, height = 0.dp))
                Text(text = "Location",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }

            Column(modifier = Modifier.padding(15.dp, 10.dp),) {
                Text(
                    "Bosnia & Herzegovina",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Gray
                )
                Spacer(Modifier.size(width = 0.dp, height = 20.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(cities.sortedWith(compareBy { it.name })) { city ->
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = city.name)
                            RadioButton(
                                selected = selectedCity == city.name,
                                onClick = { selectedCity = city.name }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview( showBackground = false, )
@Composable
fun ListOfLocationsPreview(){
    VaktijaPROTheme {
        //ListOfLocations()
    }
}