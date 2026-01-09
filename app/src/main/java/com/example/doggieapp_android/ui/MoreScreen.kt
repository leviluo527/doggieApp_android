package com.example.doggieapp_android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.doggieapp_android.ui.theme.CorgiYellow

data class MoreItem(val name: String)

val moreItems = listOf(
    MoreItem("个人信息"),
    MoreItem("宠物信息"),
    MoreItem("付款信息"),
    MoreItem("设置"),
    MoreItem("成为一个宠物管理大师"),
    MoreItem("帮助"),
)

@Composable
fun MoreScreen() {
    Column(modifier = Modifier.padding(8.dp)) {
        InviteFriendBanner()
        LazyColumn {
            items(moreItems) { item ->
                MoreListItem(item = item)
            }
        }
    }
}

@Composable
fun InviteFriendBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = CorgiYellow)
    ) {
        Text(
            text = "邀请朋友，获取100元！",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun MoreListItem(item: MoreItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { /* TODO */ }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoreScreenPreview() {
    MoreScreen()
}
