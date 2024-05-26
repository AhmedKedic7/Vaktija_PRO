package com.example.vaktijapro.ui.screen.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vaktijapro.ui.screen.AyatScreen
import com.example.vaktijapro.ui.screen.AyatScreenDestination
import com.example.vaktijapro.ui.screen.ListOfCities
import com.example.vaktijapro.ui.screen.ListOfLocations
import com.example.vaktijapro.ui.screen.LoginDestination
import com.example.vaktijapro.ui.screen.LoginScreen
import com.example.vaktijapro.ui.screen.Prayers
import com.example.vaktijapro.ui.screen.PrayersDestination
import com.example.vaktijapro.ui.screen.Register
import com.example.vaktijapro.ui.screen.RegistrationDestination

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = LoginDestination.route) {
        composable(route = RegistrationDestination.route) {
            Register(
                navigateToLogin = {
                    navController.navigate("${LoginDestination.route}")
                },
            )
        }
        composable(route = LoginDestination.route){
            LoginScreen(
                navigateToRegister ={ navController.navigate("${RegistrationDestination.route}") },
                navigateToAyatScreen = { navController.navigate("${AyatScreenDestination.route}") },
            )
        }
        composable(route = ListOfCities.route){
            ListOfLocations(
                navigateToPrayers = { navController.navigate("${PrayersDestination.route}") },
            )
        }

        composable(route = PrayersDestination.route){
            Prayers(
                navigateToCities ={ navController.navigate("${ListOfCities.route}") },
                navigateToAyatScreen = { navController.navigate("${AyatScreenDestination.route}") }
            )
        }

        composable(route = AyatScreenDestination.route){
            AyatScreen(
                navigateToAyatScreen = { navController.navigate("${AyatScreenDestination.route}") },
                navigateToPrayers = { navController.navigate("${PrayersDestination.route}") }
            )
        }
    }
}