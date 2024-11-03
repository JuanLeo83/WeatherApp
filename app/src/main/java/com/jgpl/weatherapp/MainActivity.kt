package com.jgpl.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jgpl.weatherapp.ui.navigation.Route
import com.jgpl.weatherapp.ui.screen.current.CurrentScreen
import com.jgpl.weatherapp.ui.screen.settings.SettingsScreen
import com.jgpl.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Route.CurrentScreen.name
                    ) {
                        composable(Route.CurrentScreen.name) {
                            CurrentScreen {
                                navController.navigate(Route.SettingsScreen.name)
                            }
                        }
                        composable(Route.SettingsScreen.name) {
                            SettingsScreen {
                                navController.navigateUp()
                            }
                        }
                    }
                }
            }
        }

    }
}