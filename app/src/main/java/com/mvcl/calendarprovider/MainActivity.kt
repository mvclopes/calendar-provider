package com.mvcl.calendarprovider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mvcl.calendarprovider.features.calendar.CalendarsScreen
import com.mvcl.calendarprovider.features.calendar.CalendarsViewModel
import com.mvcl.calendarprovider.ui.theme.CalendarProviderTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalendarProviderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val calendarsViewModel = koinViewModel<CalendarsViewModel>()
                    val state by calendarsViewModel.state.collectAsState()

                    CalendarsScreen(state)
                }
            }
        }
    }
}
