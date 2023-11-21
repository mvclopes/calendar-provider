package com.mvcl.calendarprovider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mvcl.calendarprovider.features.calendar.navigation.calendarsScreen
import com.mvcl.calendarprovider.features.event.navigation.eventsScreen
import com.mvcl.calendarprovider.navigation.AppRoutes
import com.mvcl.calendarprovider.ui.theme.CalendarProviderTheme

val LocalNavController = compositionLocalOf<NavHostController>{ error("No LocalNavController provided") }

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarProviderTheme {
                CompositionLocalProvider(
                    LocalNavController provides rememberNavController()
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Scaffold { innerPadding ->
                            NavHost(
                                modifier = Modifier.padding(innerPadding),
                                navController = LocalNavController.current,
                                startDestination = AppRoutes.CALENDAR_SCREEN.name,
                            ) {
                                calendarsScreen()
                                eventsScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}
