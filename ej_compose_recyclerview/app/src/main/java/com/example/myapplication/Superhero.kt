package com.example.myapplication

import android.provider.ContactsContract.DisplayPhoto
import androidx.annotation.DrawableRes

data class Superhero(
    val name: String,
    val realName: String,
    val editorial: String,
    @DrawableRes val photo: Int
)
