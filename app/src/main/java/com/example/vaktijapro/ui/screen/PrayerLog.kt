package com.example.vaktijapro.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vaktijapro.R
import com.example.vaktijapro.ui.screen.navigation.NavigationDestination
import com.example.vaktijapro.viewModel.AppViewModelProvider
import com.example.vaktijapro.viewModel.LoginRegistrationViewModel
import com.example.vaktijapro.viewModel.PrayerViewModel





object TutorialDestination: NavigationDestination {
    override val route = "tutorial"
    override val title = "Tutorial"
}
@Composable
fun PrayerLog(
    navigateToAyatScreen: () -> Unit,
    navigateToTutorial: () ->Unit,
    viewModel: PrayerViewModel = viewModel(factory = AppViewModelProvider.prayerFactory)
) {
    val prayers by viewModel.lastFivePrayers.collectAsState(initial = emptyList())
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xDF005930) // Set the background color here
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navigateToAyatScreen() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.width(500.dp) ,

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.left_arrow),
                    contentDescription = "left arrow",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Gray
                )

            }




            Spacer(modifier = Modifier.height(16.dp))
            Card {


            Text(
                text="Last five prayers prayed:",
                color=Color(0xDF005930),
                fontWeight = FontWeight.Bold,
                modifier=Modifier.padding(16.dp)

                )
            prayers.forEach { prayer ->
                Text(
                    text = prayer.prayer,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xDF005930),
                    modifier = Modifier.padding(16.dp)
                )
            }
            }
            Spacer(modifier = Modifier.height(16.dp))



                Text(
                    text = "LOG PRAYERS",
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold


                )
            Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navigateToTutorial()
                        viewModel.logPrayer("Fejr")
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(
                        text = "Prayed Fejr",
                        fontSize = 20.sp,
                        color = Color(0xDF00502B),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navigateToTutorial()
                        viewModel.logPrayer("Zhuhr")
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(
                        text = "Prayed Zhuhr",
                        fontSize = 20.sp,
                        color = Color(0xDF00502B),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
                    )


                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navigateToTutorial()
                        viewModel.logPrayer("Asr")
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(
                        text = "Prayed Asr",
                        fontSize = 20.sp,
                        color = Color(0xDF00502B),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navigateToTutorial()
                        viewModel.logPrayer("Maghrib")
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(
                        text = "Prayed Maghrib",
                        fontSize = 20.sp,
                        color = Color(0xDF00502B),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
                    )


                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navigateToTutorial()
                        viewModel.logPrayer("Isha")
                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(
                        text = "Prayed Isha",
                        fontSize = 20.sp,
                        color = Color(0xDF00502B),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 0.dp)
                    )

                }
                Spacer(modifier = Modifier.height(32.dp))


        }


    }
}