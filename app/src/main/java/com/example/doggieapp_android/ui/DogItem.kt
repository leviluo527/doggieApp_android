package com.example.doggieapp_android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.doggieapp_android.R
import com.example.doggieapp_android.data.Dog

@Composable
fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp).fillMaxWidth()) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(dog.image),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
            )
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = dog.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "${dog.age} years old | ${dog.breed}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview
@Composable
fun DogItemPreview() {
    DogItem(dog = Dog("Corgi", 1, "Corgi", R.drawable.corgi))
}
