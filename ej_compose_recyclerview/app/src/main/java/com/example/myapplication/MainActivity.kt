package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    SuperheroStickyView()
                }
            }
        }
    }
}

@Composable
fun SimpleRecyclerView(){
    val myList = listOf("Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto","Pepe", "Manolo", "Juan", "Jose", "Roberto")
    LazyColumn {
        item{
            Text(text = "Header")
        }
        /*items(7){
            Text(text = "Este es el item $it")
        }*/
        items(myList){
            Text(text = "Hola me llamo $it")
        }
        item{
            Text(text = "Footer")
        }
    }
    /*LazyRow(content = )*/
}

@Composable
fun SuperheroView(){
    val context = LocalContext.current
    LazyRow{
        items(getSuperhero()){superhero ->
            Itemhero(superhero = superhero)
            {
                Toast.makeText(context, "Nombre:${superhero.name}", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

//Cuando hagamos click devolvera el objeto superhero
//Cuidado con poner dos scroll en la misma direccion
@Composable
fun Itemhero(superhero: Superhero, onItemSelected:(Superhero)->Unit){
    Card(border = BorderStroke(1.dp, Color.Magenta), modifier = Modifier
        .width(200.dp)
        .padding(5.dp)
        .clickable { onItemSelected(superhero) }) {
        Column {
            Image(painter = painterResource(id = superhero.photo),
                contentDescription = "Avatar superhero",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp))
            Text(text = superhero.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp))
            Text(text = superhero.realName,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                fontSize = 12.sp)

            Text(text = superhero.editorial,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                fontSize = 10.sp)
        }
    }
}
fun getSuperhero(): List<Superhero>{
    return listOf(
        Superhero("Batman", "Ni idea", "DC", R.drawable.batman),
        Superhero("Lobezno", "Ni idea", "Marvel", R.drawable.lobezno),
        Superhero("Spiderman", "Peter Parker", "Marvel", R.drawable.spiderman),
        Superhero("Wonder Woman", "Ni idea", "DC", R.drawable.wonder_woman),
        Superhero("Deadpool", "Ni idea", "DC", R.drawable.deadpool),
        Superhero("Green Lantern", "Ni idea", "DC", R.drawable.green_lantern),
        Superhero("Flash", "Ni idea", "Marvel", R.drawable.flash),
    )
}

@Composable
fun SuperheroGridView(){
    val context = LocalContext.current
    //Se ajusta al numero de columnas que le pasamos, si le pasamos 3 lo ajusta automaticamente
    LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
        items(getSuperhero()){superhero ->
            Itemhero(superhero = superhero)
            {
                Toast.makeText(context, "Nombre:${superhero.name}", Toast.LENGTH_SHORT).show()
            }

        }
    })
}

@Composable
fun SuperheroViewButton(){
    val context = LocalContext.current
    val rvState = rememberLazyListState()
    LazyColumn(
        state = rvState
    ){
        items(getSuperhero()){superhero ->
            Itemhero(superhero = superhero)
            {
                Toast.makeText(context, "Nombre:${superhero.name}", Toast.LENGTH_SHORT).show()
            }

        }
    }
    val showButton by remember {
        derivedStateOf {
            rvState.firstVisibleItemIndex > 0
        }
    }

    if (showButton){
        Button(onClick = {}){
            Text(text = "Pulsar")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SuperheroStickyView(){
    val context = LocalContext.current
    val superhero = getSuperhero().groupBy { it.editorial }
    LazyColumn{

        superhero.forEach{(editorial, mysuperhero) ->
            stickyHeader {
                Card (modifier = Modifier.padding(10.dp).fillMaxWidth().border(1.dp, Color.Black)){
                    Text(text = editorial)
                }
            }
            items(mysuperhero){superhero ->
                Itemhero(superhero = superhero)
                {
                    Toast.makeText(context, "Nombre:${superhero.name}", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
