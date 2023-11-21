package com.mvcl.calendarprovider.features.reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mvcl.calendarprovider.reminder.model.ReminderEntity
import com.mvcl.calendarprovider.ui.components.BackTopBar
import com.mvcl.calendarprovider.ui.components.EmptyView
import com.mvcl.calendarprovider.ui.components.ErrorView
import com.mvcl.calendarprovider.ui.components.LoadingInCenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(
    state: ReminderViewState,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = { BackTopBar(title = "Available reminders", onBackPressed = onBackPressed) },
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding),
        ) {
            when (state) {
                is ReminderViewState.Error -> ErrorView(errorMessage = state.throwable.message)

                ReminderViewState.Idle -> Unit

                ReminderViewState.Loading -> LoadingInCenter()

                is ReminderViewState.Success -> ReminderList(state.reminders)
            }

        }
    }
}

@Composable
private fun ReminderList(reminders: List<ReminderEntity>) {
    if (reminders.isEmpty()) {
        EmptyView(message = "No available reminders")
    } else {
        LazyColumn {
            items(reminders) { reminder ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "Minutes: ${reminder.minutes}")
                        Text(text = "Method: ${reminder.method}")
                    }
                }
            }
        }
    }
}