package com.mvcl.calendarprovider.features.reminder.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mvcl.calendarprovider.LocalNavController
import com.mvcl.calendarprovider.features.reminder.RemindersScreen
import com.mvcl.calendarprovider.features.reminder.RemindersViewModel
import com.mvcl.calendarprovider.navigation.AppRoutes
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

private const val EVENT_ID_ARG = "eventId"

fun NavGraphBuilder.remindersScreen() {
    composable(
        route = "${AppRoutes.REMINDER_SCREEN}/{$EVENT_ID_ARG}",
        arguments = listOf(
            navArgument(EVENT_ID_ARG) { type = NavType.LongType }
        )
    ) { backStackEntry ->
        val navController = LocalNavController.current
        val eventId = backStackEntry.arguments?.getLong(EVENT_ID_ARG) ?: 0
        val viewModel = koinViewModel<RemindersViewModel> { parametersOf(eventId) }
        val state by viewModel.state.collectAsState()

        RemindersScreen(
            state = state,
            onBackPressed = { navController.popBackStack() }
        )
    }
}

fun NavController.navigateToRemindersScreen(eventId: Long) {
    navigate("${AppRoutes.REMINDER_SCREEN}/$eventId")
}
