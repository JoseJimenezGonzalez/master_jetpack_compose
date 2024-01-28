package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    MyDropDownMenu()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUI() {
    MyApplicationTheme {
        MyDropDownMenu()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropDownMenu() {
    val generoJuegos = listOf("Survival horror", "Acción", "Aventuras", "Disparos", "Carreras","Survival horror", "Acción", "Aventuras", "Disparos", "Carreras","Survival horror", "Acción", "Aventuras", "Disparos", "Carreras","Survival horror", "Acción", "Aventuras", "Disparos", "Carreras")
    var contenidoEditText by remember { mutableStateOf(generoJuegos[0]) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        //Este es un contenedor que proporciona la caja del menú desplegable. El contenido dentro
        //de este bloque se mostrará como el área principal del menú desplegable.
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            //Este es un componente de campo de texto que muestra el valor seleccionado actualmente
            //y actúa como el botón principal para abrir o cerrar el menú desplegable. La propiedad
            //trailingIcon se utiliza para mostrar un ícono que indica el estado del menú
            //desplegable (abierto o cerrado).
            TextField(
                value = contenidoEditText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )
            //Este es el componente real del menú desplegable. Se muestra solo cuando expanded es
            //true. Contiene DropdownMenuItem para cada elemento en generoJuegos.
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                //generoJuegos.forEach { item -> ... }: Un bucle que itera sobre cada elemento en
                //generoJuegos para construir las opciones del menú desplegable.
                generoJuegos.forEach { item ->
                    //DropdownMenuItem { ... }: Cada elemento del menú desplegable. El bloque
                    //text contiene el contenido del elemento (en este caso, un Text con el texto
                    //del juego), y onClick se ejecuta cuando se selecciona ese elemento. En este
                    //caso, actualiza contenidoEditText y cierra el menú desplegable.
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            contenidoEditText = item
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}