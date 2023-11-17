package com.mvcl.calendarprovider.features.event.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mvcl.calendarprovider.features.event.EventsScreen
import com.mvcl.calendarprovider.navigation.AppRoutes

private const val CALENDAR_ID_ARG = "calendarId"

fun NavGraphBuilder.eventsScreen() {
    composable(
        route = "${AppRoutes.EVENT_SCREEN.name}/{$CALENDAR_ID_ARG}",
        arguments = listOf(
            navArgument(CALENDAR_ID_ARG) { type = NavType.LongType }
        )
    ) { backStackEntry ->
        val calendarId = backStackEntry.arguments?.getLong(CALENDAR_ID_ARG) ?: 0
        EventsScreen(calendarId)
    }
}

fun NavController.navigateToEventsScreen(calendarId: Long) {
    navigate("${AppRoutes.EVENT_SCREEN.name}/$calendarId")
}