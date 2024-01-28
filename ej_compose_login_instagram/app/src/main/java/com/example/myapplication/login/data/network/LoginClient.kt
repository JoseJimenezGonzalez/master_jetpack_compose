package com.example.myapplication.login.data.network

import com.example.myapplication.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("v3/0d5b3c93-e0dc-464a-bdda-cf39df1162d5")
    //No los voy a usar pero es para hacerlo mas real
    suspend fun doLogin(): Response<LoginResponse>
}