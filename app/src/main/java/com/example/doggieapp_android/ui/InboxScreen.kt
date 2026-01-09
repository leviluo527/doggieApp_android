package com.example.doggieapp_android.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun InboxScreen() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("收到的请求", "发出的请求")

    Column {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> ReceivedRequestsScreen()
            1 -> SentRequestsScreen()
        }
    }
}

@Composable
fun ReceivedRequestsScreen() {
    Text(text = "这里显示收到的请求")
}

@Composable
fun SentRequestsScreen() {
    Text(text = "这里显示发出的请求")
}
