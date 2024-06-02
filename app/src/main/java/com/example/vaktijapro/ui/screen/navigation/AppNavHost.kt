package com.example.vaktijapro.ui.screen.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.vaktijapro.ui.screen.AyatScreen
import com.example.vaktijapro.ui.screen.AyatScreenDestination

import com.example.vaktijapro.ui.screen.PrayerLog
import com.example.vaktijapro.ui.screen.ListOfCities
import com.example.vaktijapro.ui.screen.ListOfLocations
import com.example.vaktijapro.ui.screen.LoginDestination
import com.example.vaktijapro.ui.screen.LoginScreen
import com.example.vaktijapro.ui.screen.Prayers
import com.example.vaktijapro.ui.screen.PrayersDestination
import com.example.vaktijapro.ui.screen.Register
import com.example.vaktijapro.ui.screen.RegistrationDestination
import com.example.vaktijapro.ui.screen.TutorialDestination

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
                navigateToAyatScreen = { userId ->
                    navController.navigate("${AyatScreenDestination.route}/$userId")
                },
            )
        }
        composable(route = ListOfCities.route){
            ListOfLocations(
                navigateToPrayers = { navController.popBackStack() },
            )
        }

        composable("${PrayersDestination.route}/{userId}"){ backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            userId?.let {
                Prayers(
                    userId = it,
                    navigateToCities = { navController.navigate("${ListOfCities.route}") },
                    navigateToAyatScreen = { navController.navigate("${AyatScreenDestination.route}/$it") }
                )
            }
        }

        composable("${AyatScreenDestination.route}/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            userId?.let {
                AyatScreen(
                    userId = it,
                    navigateToAyatScreen = { navController.navigate("${AyatScreenDestination.route}/$it") },
                    navigateToPrayers = { navController.navigate("${PrayersDestination.route}/$it") },
                    navigateToTutorial = { navController.navigate("${TutorialDestination.route}/$it") },


                    )


            }

        }


        composable("${TutorialDestination.route}/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            userId?.let {
                PrayerLog(
                    userId = it,
                    navigateToAyatScreen = { navController.navigate("${AyatScreenDestination.route}/$it") },
                    navigateToTutorial = { navController.navigate("${TutorialDestination.route}/$it")}
                )
            }
        }
    }
}