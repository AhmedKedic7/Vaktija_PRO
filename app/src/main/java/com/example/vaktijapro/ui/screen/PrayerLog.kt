package com.example.vaktijapro.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.vaktijapro.model.models.Prayer
import com.example.vaktijapro.ui.screen.navigation.NavigationDestination
import com.example.vaktijapro.viewModel.AppViewModelProvider
import com.example.vaktijapro.viewModel.LoginRegistrationViewModel
import com.example.vaktijapro.viewModel.PrayerViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun formatTimestamp(timestamp: Long): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date(timestamp))
}


object TutorialDestination: NavigationDestination {
    override val route = "tutorial"
    override val title = "Tutorial"
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrayerLog(
    userId:Int,
    navigateToAyatScreen: () -> Unit,
    navigateToTutorial: () ->Unit,
    viewModel: PrayerViewModel = viewModel(factory = AppViewModelProvider.prayerFactory)
) {


    val prayers by viewModel.lastFivePrayers(userId).collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Prayer Log") },
                navigationIcon = {
                    IconButton(onClick = {navigateToAyatScreen()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xDF005930) // Set the background color here
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Spacer(modifier = Modifier.height(56.dp))
            Box(modifier = Modifier.fillMaxSize()) {
                Card(modifier = Modifier
                    .fillMaxWidth()

                ){


                    Text(
                        text = "Last five prayers prayed:",
                        color = Color(0xDF005930),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)

                    )
                    prayers?.let { list ->
                        if (list.isNotEmpty()) {
                            list.forEach { prayer ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${prayer.prayer} - ${formatTimestamp(prayer.timestamp)}",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xDF005930),
                                        modifier = Modifier.weight(1f)
                                    )
                                    IconButton(
                                        onClick = { viewModel.deletePrayerById(prayer.prayerId) },
                                        modifier = Modifier.size(16.dp)

                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(24.dp)
                                                .background(Color.Red, shape = CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Clear,
                                                contentDescription = "Delete",
                                                tint = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = "Loading...",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xDF005930),
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Text(
                        text = "LOG PRAYERS",
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold


                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            navigateToTutorial()
                            viewModel.logPrayer("Fejr", userId)
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
                            viewModel.logPrayer("Zhuhr", userId)
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
                            viewModel.logPrayer("Asr", userId)
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
                            viewModel.logPrayer("Maghrib", userId)
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
                            viewModel.logPrayer("Isha", userId)
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
                }
            }
            Spacer(modifier = Modifier.height(32.dp))


        }
    }

    }
    )
}