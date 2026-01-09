package com.example.doggieapp_android.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dog(
    val name: String,
    val age: Int,
    val breed: String,
    @DrawableRes val image: Int,
)
