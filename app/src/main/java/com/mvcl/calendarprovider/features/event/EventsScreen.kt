package com.mvcl.calendarprovider.features.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mvcl.calendarprovider.ui.components.EmptyView
import com.mvcl.calendarprovider.ui.components.ErrorView
import com.mvcl.calendarprovider.ui.components.LoadingInCenter
import com.mvcl.calendarprovider.event.model.EventEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen(
    state: EventViewState,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text(
                        text = "Available events",
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBackPressed() }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            tint = Color.White,
                            contentDescription = null
                        )
                    }
                }
            )
        },
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
                    onEventClicked = {}
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EventList(
    events: List<EventEntity>,
    onEventClicked: (Long) -> Unit
) {
    if (events.isEmpty()) {
        EmptyView(message = "No available events")
    } else {
        LazyColumn {
            items(events) { event ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(event.displayColor)
                    ),
                    onClick = { onEventClicked(event.id) }
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = event.title)
                    }
                }
            }
        }
    }
}
