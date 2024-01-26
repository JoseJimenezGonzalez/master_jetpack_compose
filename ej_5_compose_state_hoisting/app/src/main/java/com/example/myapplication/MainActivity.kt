package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
                    //Esta variable sobrevive a las recomposiciones y actualizaciones de la interfaz de usuario
                    var myText by remember { mutableStateOf("") }
                    MyTextField(myText, {myText = it})
                }
            }
        }
    }
}

//Palabra es el valor actual y ejecuta la lambda cuando el texto cambia
@Composable
fun MyTextField(palabra: String, onValueChanged: (String) -> Unit){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val editText = createRef()
        TextField(value = palabra,
            onValueChange = {onValueChanged(it)},
            modifier = Modifier
                .constrainAs(editText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 40.dp)
        )

    }
}


@Preview
@Composable
fun DefaultPreview(){
    var palabra = "Hola mundo"
    MyTextField(palabra = palabra, onValueChanged = { palabra = it})
}