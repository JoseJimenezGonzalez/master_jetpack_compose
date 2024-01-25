package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EjercicioPrueba()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun EjercicioPrueba() {
    var contador by rememberSaveable { mutableStateOf(0) }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val boton = createRef()
        val texto = createRef()
        Button(onClick = { contador += 1}, modifier = Modifier.constrainAs(boton){
            //Restricciones
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(texto.bottom)
        }) {
            Text(text = "Pulsar")
        }
        Text(text = "La cuenta es de $contador", modifier = Modifier.constrainAs(texto){
            //Restricciones
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(boton.top)

        })
    }
}