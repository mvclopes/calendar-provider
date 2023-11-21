package com.mvcl.calendarprovider.features.event.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mvcl.calendarprovider.LocalNavController
import com.mvcl.calendarprovider.features.event.EventsScreen
import com.mvcl.calendarprovider.features.event.EventsViewModel
import com.mvcl.calendarprovider.navigation.AppRoutes
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

private const val CALENDAR_ID_ARG = "calendarId"

fun NavGraphBuilder.eventsScreen() {
    composable(
        route = "${AppRoutes.EVENT_SCREEN.name}/{$CALENDAR_ID_ARG}",
        arguments = listOf(
            navArgument(CALENDAR_ID_ARG) { type = NavType.LongType }
        )
    ) { backStackEntry ->
        val calendarId = backStackEntry.arguments?.getLong(CALENDAR_ID_ARG) ?: 0
        val navController = LocalNavController.current
        val viewModel = koinViewModel<EventsViewModel>{ parametersOf(calendarId) }
        val state by viewModel.state.collectAsState()

        EventsScreen(
            state = state,
            onBackPressed = { navController.popBackStack() }
        )
    }
}

fun NavController.navigateToEventsScreen(calendarId: Long) {
    navigate("${AppRoutes.EVENT_SCREEN.name}/$calendarId")
}