package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

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
                    PintarUI()
                }
            }
        }
    }
}


@Preview
@Composable
fun PintarUI() {
    Column {
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Blue), contentAlignment = Alignment.Center
        ) {
            //Lo que va dentro de la caja
            Text(text = "Ejemplo 1")
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Red)) {
            //Lo que va dentro de la caja
            Row {
                Box(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .background(Color.Green), contentAlignment = Alignment.Center) {
                    Text(text = "Ejemplo 2")
                }
                Spacer(modifier = Modifier.fillMaxHeight().weight(1f))
                Box(
                    Modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .background(Color.Yellow), contentAlignment = Alignment.Center) {
                    Text(text = "Ejemplo 3")
                }
            }
        }
        Box(
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Magenta), contentAlignment = Alignment.BottomCenter) {
            //Lo que va dentro de la caja
            Text(text = "Ejemplo 4")
        }
    }
}