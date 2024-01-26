package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    MyTextFieldPro()
                }
            }
        }
    }
}
@Preview
@Composable
fun DefaultPreview(){
    MyTextFieldPro()
}
//Le estamos dando demasiada responsabilidad a esta funcion, debemos de tratar que las funciones
//composables no tengan estado, en este caso lo tiene y hay que arreglarlo
@Composable
fun MyTextField() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val editText = createRef()
        var myText by remember { mutableStateOf("") }

        TextField(
            value = myText,
            onValueChange = {myText = it},
            label = { Text(text = "Introduce tu nombre") },
            modifier = Modifier
                .constrainAs(editText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 40.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldPro() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val editText = createRef()
        var myText by remember { mutableStateOf("") }

        OutlinedTextField(
            value = myText,
            onValueChange = {myText = it},
            label = { Text(text = "Introduce tu nombre") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Magenta,
                unfocusedBorderColor = Color.Blue
            ),
            modifier = Modifier
                .constrainAs(editText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 40.dp))
    }
}

