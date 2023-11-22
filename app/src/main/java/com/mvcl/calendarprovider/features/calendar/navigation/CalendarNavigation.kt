package com.mvcl.calendarprovider.features.calendar.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mvcl.calendarprovider.LocalNavController
import com.mvcl.calendarprovider.features.calendar.CalendarsScreen
import com.mvcl.calendarprovider.features.calendar.CalendarsViewModel
import com.mvcl.calendarprovider.features.event.navigation.navigateToEventsScreen
import com.mvcl.calendarprovider.navigation.AppRoutes
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.calendarsScreen() {
    composable(AppRoutes.CALENDAR_SCREEN.name) {
        val navController = LocalNavController.current
        val calendarsViewModel = koinViewModel<CalendarsViewModel>()
        val state by calendarsViewModel.state.collectAsState()

        CalendarsScreen(
            state = state,
            onNavigateToEvent = { eventArgs -> navController.navigateToEventsScreen(eventArgs) }
        )
    }
}
