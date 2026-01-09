package com.example.doggieapp_android.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.doggieapp_android.data.Dog
import com.example.doggieapp_android.data.dogs

@Composable
fun DogList(dogs: List<Dog>) {
    LazyColumn {
        items(dogs) { dog ->
            DogItem(dog = dog)
        }
    }
}

@Preview
@Composable
fun DogListPreview() {
    DogList(dogs = dogs)
}
