package com.example.myapplication.login.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
//Al padre tengo que decirle que va a recibir un login view model
//Prestar atencion a las variables remember que son las que tienen logica
@Composable
fun LoginScreeen(loginViewModel: LoginViewModel){
    Box (
        Modifier
            .fillMaxSize()
            .padding(12.dp)){
        val isLoading: Boolean by loginViewModel.isLoading.observeAsState(initial = false)
        if(isLoading){
            Column (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                androidx.compose.material.CircularProgressIndicator()
            }
        }else{
            Header(modifier = Modifier.align(Alignment.TopEnd))
            Body(modifier = Modifier.align(Alignment.Center), loginViewModel)
            Footer(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(
            Modifier
                .background(Color.LightGray)
                .height(1.dp)
                .fillMaxWidth()
        )
        SignUp(modifier = modifier)
    }
}

@Composable
fun SignUp(modifier: Modifier) {
    Row (modifier = modifier
        .fillMaxWidth()
        .padding(14.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
        Text(text = "Don't have an account?", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.LightGray)
        Text(text = "Sign Up.", color = Color(0xFF4EA8E9), fontSize = 10.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 6.dp))
    }
}

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {

    val email: String by loginViewModel.email.observeAsState(initial = "")
    val password: String by loginViewModel.password.observeAsState(initial = "")
    val isLoginEnable: Boolean by loginViewModel.isLoginEnable.observeAsState(initial = false)

    Column(modifier = modifier) {
        //Primero va la imagen
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Email(email) {
            loginViewModel.onLoginChanged(it, password)
        }
        Spacer(modifier = Modifier.size(4.dp))
        Password(password) {
            loginViewModel.onLoginChanged(email, it)
        }
        /*Esto es para que sea mas legible
        * Password(password) {
            loginViewModel.onLoginChanged(email = email, password = it)
        }
        * */
        Spacer(modifier = Modifier.size(8.dp))
        //Este es el modificador de la columna
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        LoginButton(isLoginEnable, loginViewModel)
        Spacer(modifier = Modifier.size(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.size(32.dp))
        SocialLogin()
    }
}

@Composable
fun SocialLogin() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.icon_facebook),
            contentDescription = "logo_facebook", Modifier.size(16.dp, 16.dp))
        Text(text = "Continue as Jose Jimenez", color = Color(0xFF4EA8E9), fontSize = 14.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp))
    }
}

@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color.LightGray)
                .height(1.dp)
                .weight(1f)
        )
        Text(text = "OR", Modifier.padding(horizontal = 8.dp), color = Color.LightGray, fontSize = 14.sp)
        Divider(
            Modifier
                .background(Color.LightGray)
                .height(1.dp)
                .weight(1f)
        )

    }
}

@Composable
fun LoginButton(isloginEnable: Boolean, loginViewModel: LoginViewModel) {
    Button(onClick = { loginViewModel.onLoginSelected() }, enabled = isloginEnable, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF266894),
            disabledContainerColor = Color(0XFF78C8F9),
        )) {
        Text(text = "Log in")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Email(email: String, onTextChanged: (String) -> Unit) {
    TextField(value = email, onValueChange = {onTextChanged(it)}, modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email")},
        maxLines = 1, singleLine = true,
        //El teclado provee de las teclas que puedas necesitar segun el tipo
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF1F0F0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(password: String, onTextChanged: (String) -> Unit) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(value = password, onValueChange = {onTextChanged(it)},modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password")},
        maxLines = 1, singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if(passwordVisibility){
                Icons.Filled.VisibilityOff
            }else{
                Icons.Filled.Visibility
            }
            IconButton(onClick = {passwordVisibility = !passwordVisibility}) {
                Icon(imageVector = image, contentDescription = "password_visibility")
            }
        },
        visualTransformation = if(passwordVisibility){ VisualTransformation.None }else{ PasswordVisualTransformation() },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFF1F0F0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.logo_instagram), contentDescription = "logo_instagram", modifier = modifier.size(280.dp))
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(text = "Forgot the password?", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4EA8E9), modifier = modifier)
}

@Composable
fun Header(modifier: Modifier) {
    //Para que se cierre al cerrar la aplicacion
    val activity = LocalContext.current as Activity
    Icon(painter = painterResource(R.drawable.ic_clear), contentDescription = "close_app", modifier = modifier.clickable { activity.finish() })
}


