package com.mvcl.calendarprovider.features.calendar

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.mvcl.calendarprovider.R
import com.mvcl.calendarprovider.calendar.model.CalendarEntity
import com.mvcl.calendarprovider.features.event.mapper.toEventArgs
import com.mvcl.calendarprovider.features.event.model.EventArgs
import com.mvcl.calendarprovider.ui.components.CustomTopBar
import com.mvcl.calendarprovider.ui.components.EmptyView
import com.mvcl.calendarprovider.ui.components.ErrorView
import com.mvcl.calendarprovider.ui.components.LoadingInCenter

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CalendarsScreen(
    state: CalendarViewState,
    onNavigateToEvent: (EventArgs) -> Unit
) {
    val calendarPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
        )
    )

    Scaffold(
        topBar = { CustomTopBar(title = "Available calendars") },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = null)
            }

        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (calendarPermissions.allPermissionsGranted) {
                CalendarView(state, onNavigateToEvent)
            } else {
                RequestCalendarPermissions(calendarPermissions)
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun RequestCalendarPermissions(calendarPermissions: MultiplePermissionsState) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        val textToShow = if (calendarPermissions.shouldShowRationale)
            R.string.calendar_should_show_rationale
        else R.string.calendar_denied_permission_explication

        Text(stringResource(id = textToShow))

        Button(
            onClick = { calendarPermissions.launchMultiplePermissionRequest() }
        ) {
            Text(stringResource(id = R.string.request_permission_button_label))
        }
    }
}

@Composable
private fun CalendarView(
    state: CalendarViewState,
    onNavigateToEvent: (EventArgs) -> Unit
) {
    when (state) {
        is CalendarViewState.Error -> ErrorView(errorMessage = state.throwable.message)

        CalendarViewState.Idle -> Unit

        CalendarViewState.Loading -> LoadingInCenter()

        is CalendarViewState.Success -> CalendarList(
            calendars = state.calendars,
            onCardClicked = onNavigateToEvent
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarList(
    calendars: List<CalendarEntity>,
    onCardClicked: (EventArgs) -> Unit
) {
    if (calendars.isEmpty()) {
        EmptyView(message = "No available calendars")
    } else {
        LazyColumn {
            items(calendars) { calendar ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(calendar.color)
                    ),
                    onClick = { onCardClicked(calendar.toEventArgs()) }
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "Display name: ${calendar.displayName}")
                        Text(text = "Owner account: ${calendar.ownerAccount}")
                        Text(text = "Max reminders allowed: ${calendar.maxRemindersAllowed}")
                    }
                }
            }
        }
    }
}