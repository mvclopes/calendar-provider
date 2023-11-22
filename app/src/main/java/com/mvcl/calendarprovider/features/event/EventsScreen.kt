package com.mvcl.calendarprovider.features.event

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mvcl.calendarprovider.event.model.EventEntity
import com.mvcl.calendarprovider.ui.components.BackTopBar
import com.mvcl.calendarprovider.ui.components.EmptyView
import com.mvcl.calendarprovider.ui.components.ErrorView
import com.mvcl.calendarprovider.ui.components.LoadingInCenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    state: EventViewState,
    onBackPressed: () -> Unit,
    onEventClicked: (Long) -> Unit,
    onDeleteEvent: (Long) -> Unit,
) {
    Scaffold(
        topBar = { BackTopBar(title = "Available events", onBackPressed = onBackPressed) },
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (state) {
                is EventViewState.Error -> ErrorView(errorMessage = state.throwable.message)

                EventViewState.Idle -> Unit

                EventViewState.Loading -> LoadingInCenter()

                is EventViewState.Success -> EventList(
                    events = state.events,
                    onEventClicked = onEventClicked,
                    onDeleteEvent = onDeleteEvent
                )
            }

        }
    }
}

@Composable
private fun EventList(
    events: List<EventEntity>,
    onEventClicked: (Long) -> Unit,
    onDeleteEvent: (Long) -> Unit,
) {
    if (events.isEmpty()) {
        EmptyView(message = "No available events")
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            items(events) { event ->
                EventItem(
                    event = event,
                    onEventClicked = onEventClicked,
                    onDeleteEvent = onDeleteEvent
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EventItem(
    event: EventEntity,
    onEventClicked: (Long) -> Unit,
    onDeleteEvent: (Long) -> Unit,
) {
    Card(
        modifier = Modifier.padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(event.displayColor)
        )
    ) {
        ListItem(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onEventClicked(event.id) },
            colors = ListItemDefaults.colors(
                containerColor = Color.Transparent
            ),
            headlineText = { Text(text = event.title) },
            trailingContent = {
                IconButton(onClick = { onDeleteEvent(event.id) }) {
                    Icon(
                        Icons.Filled.Delete,
                        tint = Color.Red,
                        contentDescription = null
                    )
                }
            }
        )
    }
}
