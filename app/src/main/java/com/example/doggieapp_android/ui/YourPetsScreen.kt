package com.example.doggieapp_android.ui

import androidx.compose.runtime.Composable
import com.example.doggieapp_android.data.dogs

@Composable
fun YourPetsScreen() {
    DogList(dogs = dogs)
}
