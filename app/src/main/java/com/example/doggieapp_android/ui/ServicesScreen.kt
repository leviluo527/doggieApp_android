package com.example.doggieapp_android.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Service(val name: String, val description: String)

val services = listOf(
    Service("宠物看护", "专业看护，给您的爱宠一个安全、舒适的临时小窝"),
    Service("宠物寄养", "出远门也不怕，我们为您的爱宠提供家一般的温暖"),
    Service("上门喂养", "定时上门，让您的爱宠在熟悉的环境中也能吃饱喝足"),
    Service("遛狗", "风雨无阻，满足狗狗的日常运动需求，释放无限活力"),
    Service("宠物训练", "专业训犬师，帮助您的狗狗培养良好习惯，更加听话懂事"),
    Service("宠物医生", "在线问诊，专业兽医为您解答爱宠的健康问题，守护健康"),
)

@Composable
fun ServicesScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(services) { service ->
            ServiceItem(service = service, navController = navController)
        }
    }
}

@Composable
fun ServiceItem(service: Service, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { 
                navController.navigate("service_detail/${service.name}/${service.description}")
            },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = service.name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = service.description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServicesScreenPreview() {
    ServicesScreen(rememberNavController())
}
