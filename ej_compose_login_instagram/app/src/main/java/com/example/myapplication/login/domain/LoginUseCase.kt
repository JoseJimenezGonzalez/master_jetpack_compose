package com.example.myapplication.login.domain

import com.example.myapplication.login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val respository: LoginRepository){
    suspend operator fun invoke(user: String, pasdword: String): Boolean{
        return respository.doLogin(user, pasdword)
    }
}