package com.example.myapplication.ui.theme

sealed class Routes(val route: String) {
    //Si tengo que cambiar alguna id se hace aqui para no tener que cambiar el proyecto e ir buscando coincidencias
    object Screen1: Routes("screen1")
    object Screen2: Routes("screen2")
    object Screen3: Routes("screen3")
    object Screen4: Routes("screen4")
}