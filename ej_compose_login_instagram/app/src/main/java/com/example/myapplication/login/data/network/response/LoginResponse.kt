package com.example.myapplication.login.data.network.response

import com.google.gson.annotations.SerializedName
//El nombre verdadero es successs pero lo cambiamos
data class LoginResponse(
    @SerializedName("success")
    val success: Boolean
)
