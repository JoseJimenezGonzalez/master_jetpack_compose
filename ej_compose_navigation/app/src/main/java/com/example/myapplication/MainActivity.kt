package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Routes

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
                    val navigationController = rememberNavController()
                    NavHost(navController = navigationController, startDestination = Routes.Screen1.route){
                        composable(Routes.Screen1.route){ Screen1(navigationController) }
                        composable(Routes.Screen2.route){ Screen2(navigationController) }
                        composable(Routes.Screen3.route){ Screen3(navigationController) }
                        //Le ponemos un argumento de nombre name, dentro del backStackEntry esta el argumento name
                        composable(Routes.Screen4.route + "/{name}"){ backStackEntry ->
                            Screen4(
                                navigationController,
                                backStackEntry.arguments?.getString("name").orEmpty()) }
                    }
                }
            }
        }
    }
}

@Composable
fun Screen1(navigationController: NavHostController) {
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan)){
        Text(text = "Esta es la pantalla 1", modifier = Modifier.align(Alignment.Center).clickable { navigationController.navigate(Routes.Screen2.route) })
    }
}

@Composable
fun Screen2(navigationController: NavHostController) {
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)){
        Text(text = "Esta es la pantalla 2", modifier = Modifier.align(Alignment.Center).clickable { navigationController.navigate(Routes.Screen3.route) })
    }
}

@Composable
fun Screen3(navigationController: NavHostController) {
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)){
        Text(text = "Esta es la pantalla 3", modifier = Modifier.align(Alignment.Center).clickable { navigationController.navigate(Routes.Screen4.route + "/JoseJimenezGonzalez") })
    }
}

@Composable
fun Screen4(navigationController: NavHostController, name: String) {
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.Blue)){
        Text(text = name, modifier = Modifier.align(Alignment.Center).clickable { navigationController.navigate(Routes.Screen1.route) })
    }
}