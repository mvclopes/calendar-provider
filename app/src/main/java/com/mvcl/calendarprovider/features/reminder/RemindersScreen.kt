package com.mvcl.calendarprovider.features.reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mvcl.calendarprovider.features.reminder.components.AddReminderDialog
import com.mvcl.calendarprovider.reminder.model.ReminderDTO
import com.mvcl.calendarprovider.reminder.model.ReminderEntity
import com.mvcl.calendarprovider.ui.components.BackTopBar
import com.mvcl.calendarprovider.ui.components.EmptyView
import com.mvcl.calendarprovider.ui.components.ErrorView
import com.mvcl.calendarprovider.ui.components.LoadingInCenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(
    state: ReminderViewState,
    onBackPressed: () -> Unit,
    onCreateReminder: (ReminderDTO) -> Unit,
    onDeleteReminder: (Long) -> Unit
) {
    var shouldShowDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { BackTopBar(title = "Available reminders", onBackPressed = onBackPressed) },
        floatingActionButton = {
            FloatingActionButton(onClick = { shouldShowDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding),
        ) {
            when (state) {
                is ReminderViewState.Error -> ErrorView(errorMessage = state.throwable.message)

                ReminderViewState.Idle -> Unit

                ReminderViewState.Loading -> LoadingInCenter()

                is ReminderViewState.Success -> ReminderList(
                    reminders = state.reminders,
                    onDeleteReminder = onDeleteReminder
                )
            }

            if (shouldShowDialog) {
                AddReminderDialog(
                    onDismiss = { shouldShowDialog = false },
                    onCreateReminder = { minutes, method ->
                        onCreateReminder(ReminderDTO(minutes, method))
                    }
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReminderList(
    reminders: List<ReminderEntity>,
    onDeleteReminder: (Long) -> Unit
) {
    if (reminders.isEmpty()) {
        EmptyView(message = "No available reminders")
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            items(reminders) { reminder ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                ) {
                    ListItem(
                        colors = ListItemDefaults.colors(
                            containerColor = Color.LightGray
                        ),
                        headlineText = { Text(text = "Minutes: ${reminder.minutes}") },
                        supportingText = { Text(text = "Method: ${reminder.method}") },
                        trailingContent = {
                            IconButton(onClick = { onDeleteReminder(reminder.id) }) {
                                Icon(
                                    Icons.Filled.Delete,
                                    tint = Color.Red,
                                    contentDescription = null
                                )
                            }
                        }
                    )

//                    Column(
//                        modifier = Modifier.padding(8.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Text(text = "Minutes: ${reminder.minutes}")
//                        Text(text = "Method: ${reminder.method}")
//                    }
                }
            }
        }
    }
}