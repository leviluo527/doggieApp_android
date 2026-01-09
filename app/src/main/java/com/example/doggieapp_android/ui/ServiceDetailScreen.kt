package com.example.doggieapp_android.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class PetSelection(val type: String, var count: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceDetailScreen(serviceName: String, serviceDescription: String) {
    var petSelections by remember { mutableStateOf(listOf(PetSelection("狗", 1))) }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var address by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = serviceName, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = serviceDescription, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "多少宠物", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        petSelections.forEachIndexed { index, selection ->
            PetSelectionRow(selection = selection) { newSelection ->
                val newList = petSelections.toMutableList()
                newList[index] = newSelection
                petSelections = newList
            }
        }

        Button(onClick = { petSelections = petSelections + PetSelection("狗", 1) }) {
            Text("添加其他宠物")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "日期", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showDatePicker = true }) {
            Text(text = selectedDate?.let { SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(it)) } ?: "选择日期")
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { 
                        selectedDate = datePickerState.selectedDateMillis
                        showDatePicker = false 
                    }) {
                        Text("确认")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("取消")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "地址", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("输入您的地址") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { /* TODO: Handle submission */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("提交请求")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetSelectionRow(selection: PetSelection, onSelectionChange: (PetSelection) -> Unit) {
    val petTypes = listOf("狗", "小狗", "猫")
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.weight(1f)) {
            ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                TextField(
                    value = selection.type,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    petTypes.forEach { type ->
                        DropdownMenuItem(
                            text = { Text(type) },
                            onClick = {
                                onSelectionChange(selection.copy(type = type))
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (selection.count > 0) onSelectionChange(selection.copy(count = selection.count - 1)) }) {
                Icon(Icons.Default.Remove, contentDescription = "减少数量")
            }
            Text(text = "${selection.count}", style = MaterialTheme.typography.bodyLarge)
            IconButton(onClick = { onSelectionChange(selection.copy(count = selection.count + 1)) }) {
                Icon(Icons.Default.Add, contentDescription = "增加数量")
            }
        }
    }
}
