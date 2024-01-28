package com.example.myapplication.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {
    //Aqui tiene que estar toda la lógica, la view solo tiene que ser eso, una vista, nada de logica.
    //Modo pro
    //email
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    //password
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    //para ver si el boton puede ser clicado o no
    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    //para ver si mostramos el circular bar
    private val _isLoadin = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoadin

    fun onLoginChanged(email: String, password: String){
        _email.value = email
        _password.value = password
        _isLoginEnable.value = enableLogin(email, password)

    }

    fun enableLogin(email: String, password: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun onLoginSelected(){
        viewModelScope.launch {
            _isLoadin.value = true
            Log.e("Dentro del scope", "Result OK")
            val result = loginUseCase(email.value!!, password.value!!)
            if(result){
                //Navegar a la siguiente actividad
                Log.e("Siguiente actividad", "Result OK")
            }
            _isLoadin.value = false
        }
    }
}