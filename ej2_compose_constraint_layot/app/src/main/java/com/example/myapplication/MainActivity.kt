package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
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
                    EjemploConstraintLayout()
                }
            }
        }
    }
}



@Composable
fun EjemploConstraintLayout() {
    ConstraintLayout(Modifier.fillMaxSize()){

        val (cajaAmarilla, cajaAzul, cajaCian, cajaRoja, cajaVerde, cajaGris, cajaMagenta, cajaBlanca) = createRefs()

        Box(modifier = Modifier
            .size(100.dp, 50.dp)
            .background(Color.Yellow)
            .constrainAs(cajaAmarilla) {
                //Aqui es donde vamos a realizar los enganches
                top.linkTo(parent.top)
                start.linkTo(parent.start)
            })

        Box(modifier = Modifier
            .size(100.dp, 50.dp)
            .background(Color.White)
            .constrainAs(cajaBlanca) {
                //Aqui es donde vamos a realizar los enganches
                bottom.linkTo(cajaAzul.top)
                end.linkTo(cajaAzul.start)
            })

        Box(modifier = Modifier
            .size(100.dp, 50.dp)
            .background(Color.Gray)
            .constrainAs(cajaGris) {
                start.linkTo(parent.start)
                top.linkTo(cajaAmarilla.bottom)
            })

        Box(modifier = Modifier
            .size(100.dp, 50.dp)
            .background(Color.Magenta)
            .constrainAs(cajaMagenta) {
                start.linkTo(cajaAzul.end)
                bottom.linkTo(cajaAzul.top)
            })

        Box(modifier = Modifier
            .size(100.dp, 50.dp)
            .background(Color.Blue)
            .constrainAs(cajaAzul) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })

        Box(modifier = Modifier
            .size(100.dp, 50.dp)
            .background(Color.Cyan)
            .constrainAs(cajaCian) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            })

        Box(modifier = Modifier
            .size(100.dp, 50.dp)
            .background(Color.Red)
            .constrainAs(cajaRoja) {
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            })

        Box(modifier = Modifier
            .size(100.dp, 50.dp)
            .background(Color.Green)
            .constrainAs(cajaVerde) {
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            })

    }
}


@Composable
fun OtroEjemploConstraintLayout(){
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val cajaRoja = createRef()
        val topGuide = createGuidelineFromTop(0.1f)

        Box(modifier = Modifier
            .size(250.dp)
            .background(Color.Red)
            .constrainAs(cajaRoja) {
                top.linkTo(topGuide)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}


@Composable
fun OtroEjemplo(){
    ConstraintLayout (modifier = Modifier.fillMaxSize()){
        val (cajaRoja, cajaAmarilla) = createRefs()

        Box(modifier = Modifier
            .size(250.dp)
            .background(Color.Red)
            .constrainAs(cajaRoja) {
                top.linkTo(cajaAmarilla.bottom)
                start.linkTo(cajaAmarilla.end)
            })

        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow)
            .constrainAs(cajaAmarilla) {
                top.linkTo(parent.top, margin = 30.dp)
                start.linkTo(parent.start, margin = 30.dp)
            })
    }
}

@Preview
@Composable
fun CadenasConstraintExample(){
    ConstraintLayout (modifier = Modifier.fillMaxSize()){
        val (cajaRoja, cajaAmarilla, cajaAzul) = createRefs()

        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Red)
            .constrainAs(cajaRoja) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Yellow)
            .constrainAs(cajaAmarilla) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Box(modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)
            .constrainAs(cajaAzul) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        createVerticalChain(cajaRoja, cajaAmarilla, cajaAzul, chainStyle = ChainStyle.Spread)
    }
}

