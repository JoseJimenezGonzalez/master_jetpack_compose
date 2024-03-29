package com.example.myapplication.login.data.network.response

import com.example.myapplication.login.data.network.LoginClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(private val loginClient: LoginClient){

    suspend fun doLogin(user: String, password: String): Boolean{
        return withContext(Dispatchers.IO){
            val response = loginClient.doLogin()
            response.body()?.success?: false
        }
    }
}