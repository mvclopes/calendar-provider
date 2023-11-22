package com.mvcl.calendarprovider.features.event.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mvcl.calendarprovider.LocalNavController
import com.mvcl.calendarprovider.features.event.model.EventArgs
import com.mvcl.calendarprovider.features.event.EventsScreen
import com.mvcl.calendarprovider.features.event.EventsViewModel
import com.mvcl.calendarprovider.features.reminder.navigation.navigateToRemindersScreen
import com.mvcl.calendarprovider.navigation.AppRoutes
import com.mvcl.calendarprovider.navigation.createNavArgument
import com.mvcl.calendarprovider.navigation.getArgsFromBackStackEntry
import com.mvcl.calendarprovider.navigation.navigateWithArgs
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

private const val EVENT_ARG_KEY = "event_args"

fun NavGraphBuilder.eventsScreen() {
    composable(
        route = "${AppRoutes.EVENT_SCREEN}/{$EVENT_ARG_KEY}",
        arguments = listOf(
            createNavArgument<EventArgs>(EVENT_ARG_KEY)
        )
    ) { backStackEntry ->
        val args = backStackEntry.getArgsFromBackStackEntry<EventArgs>(key = EVENT_ARG_KEY)
        val navController = LocalNavController.current
        val viewModel = koinViewModel<EventsViewModel> { parametersOf(args) }
        val state by viewModel.state.collectAsState()

        EventsScreen(
            state = state,
            onBackPressed = { navController.popBackStack() },
            onEventClicked = { eventId -> navController.navigateToRemindersScreen(eventId) },
            onDeleteEvent = { eventId -> viewModel.deleteEvent(eventId) }
        )
    }
}

fun NavController.navigateToEventsScreen(arg: EventArgs) {
    navigateWithArgs(route = AppRoutes.EVENT_SCREEN.name, arg = arg)
}
