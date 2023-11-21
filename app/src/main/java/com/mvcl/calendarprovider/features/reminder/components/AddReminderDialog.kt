package com.mvcl.calendarprovider.features.reminder.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mvcl.calendarprovider.reminder.mapper.toReminderMethod
import com.mvcl.calendarprovider.reminder.model.ReminderMethod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderDialog(
    onDismiss: () -> Unit,
    onCreateReminder: (minutes: Int, method: ReminderMethod) -> Unit
) {
    var minutes by remember { mutableStateOf("") }
    val reminderMethods = ReminderMethod.values().map { it.name }
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(reminderMethods[2]) }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Surface(
            shape = ShapeDefaults.Large,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Reminder Infos",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    OutlinedTextField(
                        value = minutes,
                        shape = ShapeDefaults.Large,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text(text = "Minutes") },
                        trailingIcon = {
                            if (minutes.isEmpty())
                                return@OutlinedTextField
                            else IconButton(
                                modifier = Modifier.size(16.dp),
                                onClick = { minutes = "" }
                            ) {
                                Icon(Icons.Default.Clear, contentDescription = null)
                            }
                        },
                        onValueChange = { newValue -> minutes = newValue },
                    )

                    ReminderMethodGroup(reminderMethods, selectedOption, onOptionSelected)

                    ElevatedButton(
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        onClick = {
                            onCreateReminder(
                                minutes.toInt(),
                                selectedOption.toReminderMethod()
                            )
                        }
                    ) {
                        Text(
                            text = "Create reminder",
                            letterSpacing = 2.sp
                        )
                    }

                }
            }
        }
    }
}

@Composable
private fun ReminderMethodGroup(
    reminderMethods: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    Text(text = "Reminder method", fontWeight = FontWeight.SemiBold)

    Column(
        modifier = Modifier.selectableGroup()
    ) {
        reminderMethods.forEach { method ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .selectable(
                        selected = (method == selectedOption),
                        onClick = { onOptionSelected(method) },
                        role = Role.RadioButton
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (method == selectedOption),
                    onClick = null
                )
                Text(
                    text = method,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddReminderDialogPreview() {
    AddReminderDialog(
        onDismiss = {},
        onCreateReminder = { minutes, method -> }
    )
}