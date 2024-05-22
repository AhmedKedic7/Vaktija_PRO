package com.example.vaktijapro.ui.screen

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vaktijapro.R
import com.example.vaktijapro.ui.theme.VaktijaPROTheme
import kotlinx.coroutines.delay
import java.lang.String.format
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.time.Duration

val DAWN = "03:01"
val SUNRISE = "05:11"
val DHUHR = "13:00"
val ASR = "16:46"
val MAGHRIB = "20:15"
val ISHA = "22:07"




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Prayers()  {
    val CURRENT_PRAYER = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color(backgroundColor),
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .shadow(elevation = 8.dp)
                    .background(Color.White)
                    .height(48.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(32.dp, 0.dp),
                    fontSize = 20.sp,
                    text = "Some City",
                    color = Color.Gray
                )
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.kebab_menu),
                        contentDescription = "left arrow",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.size(width = 0.dp, height = 10.dp))
            PrayerCard(time = DAWN, prayerName = "DAWN")
            PrayerCard(time = SUNRISE, prayerName = "SUNRISE")
            PrayerCard(time = DHUHR, prayerName = "DHUHR")
            PrayerCard(time = ASR, prayerName = "ASR")
            PrayerCard(time = MAGHRIB, prayerName = "MAGHRIB")
            PrayerCard(time = ISHA, prayerName = "ISHA")

        }
    }
}

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentTime(time:String) {
    val prayerTimeSeconds = remember { mutableStateOf(LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()) }

    val currentTime = remember { mutableStateOf(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))) }

    val hoursToPray = remember { mutableStateOf(0) }
    val minutesToPray = remember { mutableStateOf(0) }
    val secondsToPray = remember { mutableStateOf(0) }

    val dawnTimeSeconds = remember { mutableStateOf(LocalTime.parse(DAWN, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()) }
    val sunRiseTimeSeconds = remember { mutableStateOf(LocalTime.parse(SUNRISE, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()) }
    val dhuhrTimeSeconds = remember { mutableStateOf(LocalTime.parse(DHUHR, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()) }
    val asrTimeSeconds = remember { mutableStateOf(LocalTime.parse(ASR, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()) }
    val maghribTimeSeconds = remember { mutableStateOf(LocalTime.parse(MAGHRIB, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()) }
    val ishaTimeSeconds = remember { mutableStateOf(LocalTime.parse(ISHA, DateTimeFormatter.ofPattern("HH:mm")).toSecondOfDay()) }

    var currentPrayer = ""

    LaunchedEffect(key1 = currentTime) {
        while (true) {
            delay(1000L)
            val currentTimeSeconds = (LocalTime.now()).toSecondOfDay()
            currentTime.value = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
            val timeToPray = abs(prayerTimeSeconds.value - LocalTime.now().toSecondOfDay())

            currentPrayer = when {
                currentTimeSeconds >= dawnTimeSeconds.value && currentTimeSeconds <= sunRiseTimeSeconds.value -> "DAWN"
                currentTimeSeconds >= sunRiseTimeSeconds.value && currentTimeSeconds <= dhuhrTimeSeconds.value -> "SUNRISE"
                currentTimeSeconds >= dhuhrTimeSeconds.value && currentTimeSeconds <= asrTimeSeconds .value -> "DHUHR"
                currentTimeSeconds >= asrTimeSeconds.value && currentTimeSeconds <= maghribTimeSeconds.value -> "ASR"
                currentTimeSeconds >= maghribTimeSeconds.value && currentTimeSeconds <= ishaTimeSeconds.value -> "MAGHRIB"
                else -> "ISHA"
            }

            if(currentTimeSeconds >= ishaTimeSeconds.value) {
                ishaTimeSeconds.value += 86400
                prayerTimeSeconds.value += 86400
            }

            if(prayerTimeSeconds.value <= currentTimeSeconds) {
                hoursToPray.value = 0
                minutesToPray.value = 0
                secondsToPray.value = 0
            } else {
                hoursToPray.value = (timeToPray / 3600)
                minutesToPray.value = ((timeToPray % 3600) / 60)
                secondsToPray.value = (timeToPray % 60)
            }
        }
    }


    /*Text(text = "$currentTimeSeconds")
    Text(text = "$prayerTimeSeconds")*/
    Text(text = String.format("%02d:%02d:%02d", hoursToPray.value, minutesToPray.value, secondsToPray.value), color = Color.Gray)
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PrayerCard(time: String, prayerName: String) {
    Column(modifier = Modifier.padding(10.dp, 0.dp)) {
        Row(
            modifier = Modifier
                .shadow(elevation = 4.dp)
                .border(2.dp, Color.White, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .background(Color.White)
                .height(120.dp)
                .padding(32.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Text(text = time, fontSize = 48.sp, color = Color.Gray)
            Spacer(modifier = Modifier.size(width = 24.dp, height = 0.dp))
            Column() {
                Spacer(modifier = Modifier.size(width = 0.dp, height = 18.dp))
                Text(text = prayerName, fontSize = 18.sp, fontWeight = FontWeight.Medium)
                CurrentTime(time)
                Spacer(modifier = Modifier.size(width = 0.dp, height = 16.dp))
            }
        }
        Spacer(modifier = Modifier.size(width = 0.dp, height = 8.dp))
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview( showBackground = false, )
@Composable
fun PrayersPreview(){
    VaktijaPROTheme {
        Prayers();
    }
}