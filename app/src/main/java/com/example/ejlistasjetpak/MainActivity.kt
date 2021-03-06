package com.example.ejlistasjetpak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.example.ejlistasjetpak.ui.theme.EjListasJetpakTheme
import androidx.navigation.compose.rememberNavController as rememberNavController
import com.example.ejlistasjetpak.MyCountrys as MyCountrys

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjListasJetpakTheme() {
                ScreenNavigation()
            }
        }
    }
}


// Pantalla 1
@Composable
fun MyCountrys(country: List<objCountry>, navController: NavController){
    LazyColumn(){
        items(country){ country ->
            MyComponent(country, navController)
        }
    }
}

// Visualizacion de datos
@Composable
fun MyComponent(country: objCountry, navController: NavController){
    Row(modifier = Modifier
        .padding(top = 6.dp, start = 6.dp)
        .clickable {
        navController.navigate("sCountry")
        }
    ){
        MyImage(country.bandera)
        MyTexts(country.nombre, itsUE(country.ue))
        }
    }

@Composable
fun MyImage(Image: String){
    AsyncImage(model = Image,
        contentDescription = "Bandera",
        modifier = Modifier
            //.background(Color.Gray)
            .size(150.dp)
            .fillMaxWidth())
}

@Composable
fun MyTexts(nombre: String, ue: String){
    Column(modifier = Modifier.padding(top = 10.dp, start = 8.dp)) {
        MyText(text = nombre,
        MaterialTheme.colors.secondaryVariant,
        MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(8.dp))
        Row(){
            MyText(
                text = "UE = ",
                MaterialTheme.colors.onBackground,
                MaterialTheme.typography.caption)
            MyText(
                text = ue,
                MaterialTheme.colors.primary,
                MaterialTheme.typography.caption)
        }
        
    }
}

@Composable
fun MyText(text: String, color: Color, textStyle: TextStyle){
    Text(text = text, color = color, style = textStyle)
}

// Pantalla 2
@Composable
fun MyCountry(pais: Paises, navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyImage(pais.bandera)
        Spacer(modifier = Modifier.height(12.dp))
        MyText(
            text = "Pa??s: " + pais.nombre,
            color = MaterialTheme.colors.onBackground ,
            textStyle = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(12.dp))
        MyText(
            text = "Capital: "+pais.capital,
            color = MaterialTheme.colors.onBackground,
            textStyle = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(12.dp))
        MyText(
            text = "Idioma: " + pais.idioma,
            color = MaterialTheme.colors.onBackground,
            textStyle = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(12.dp))
        MyText(
            text = "Poblaci??n: " + pais.poblacion.toString()+ "hab.",
            color = MaterialTheme.colors.onBackground,
            textStyle = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(12.dp))
        MyText(
            text = "Superficie: "+pais.superficie.toString() + "Km2",
            color = MaterialTheme.colors.onBackground,
            textStyle = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(12.dp))
        MyText(
            text = "Uni??n Europea -> " +itsUE(pais.UE),
            color = MaterialTheme.colors.onBackground,
            textStyle = MaterialTheme.typography.body1)
    }
}

// Navegacion entre pantallas
@Composable
fun ScreenNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "countrys"){
        composable("countrys") { MyCountrys(countryList(), navController) }
        composable("sCountry"){ MyCountry(mListPaises()[0], navController)}
    }
}
// Preview
@Preview(showSystemUi = true)
@Composable
fun PreviewComponent(){
    EjListasJetpakTheme() {
        ScreenNavigation()
    }
}


// Datos y formato de datos
data class objCountry(val nombre: String, val bandera: String, val ue: Boolean)

fun countryList(): List<objCountry>{
    val countrylist = mutableListOf<objCountry>()
    val mlisPaisesIt = mListPaises().iterator()
    for (i in mlisPaisesIt){
        val country = objCountry(i.nombre, i.bandera, i.UE)
        countrylist.add(country)
    }
    return countrylist
}

fun mListPaises(): MutableList<Paises>{
    val Albania = Paises("Albania", "Tirana", 3038594, "https://www.euractiv.com/wp-content/uploads/sites/2/2014/06/flag_of_albanian_clear_communication_people.jpeg", "Alban??s", 28748, false)
    val Alemania = Paises("Alemania", "Berl??n", 83155031, "https://static.quizur.com/i/b/59ee75286be9e5.0767061359ee7528442b66.92834076.png", "Alem??n",357375, true)
    val Andorra = Paises("Andorra", "Andorra la Vieja", 79877, "https://www.banderasphonline.com/wp-content/uploads/2020/03/comprar-bandera-andorra-para-mastil-exterior-interior.png", "Catal??n", 468, false)
    val Armenia = Paises("Armenia", "Erev??n", 3001643, "bandera", "Armenio", 29743, false)
    val Austria = Paises("Austria", "Viena", 8935112, "bandera", "Alem??n", 83871, true)
    val Azerbaiyan = Paises("Azerbaiy??n","Bak??" , 10000000, "bandera", "Azer??", 86600, false)
    val Belgica = Paises("B??lgica", "Bruselas",1152200, "bandera", "Neerland??s", 30528,  true)
    val Bielorrusia = Paises("Bielorrusia", "Minsk", 9413446, "bnadera", "Bieloruso", 207600, false)
    val BosniayHerzegovina = Paises("Bosnia y Herzegovina", "Sarajevo", 3281000, "bandera", "Bosnio", 51197, false)
    val Bulgaria = Paises("Bulgaria", "Sof??a", 6951481, "bandera", "B??lgaro", 110879, true)
    val Chipre = Paises("Chipre", "Nicosia", 958800, "bandera", "Griego", 9251,true)
    val CiudadVaticano = Paises("Ciudad del Vaticano", "Ciudad del vaticano", 618, "bandera", "Ninguno", 0, false)
    val Croacia = Paises("Croacia", "Zagreb", 4105000, "bandera", "Croata", 59594, true)
    val Dinamarca = Paises("Dinamarca", "Copenhague", 5837213, "bandera", "Dan??s", 43094, true)
    val Eslovaquia = Paises("Eslovaquia", "Bratislava", 5464060, "bandera", "Eslovaco", 49035, true)
    val Eslovenia = Paises("Eslovenia", "Liubliana", 2100126, "bandera", "Esloveno", 20273 ,true)
    val Espa??a = Paises("Espa??a", "Madrid", 47450795, "https://i.pinimg.com/originals/01/45/a3/0145a344c20782e6a4e6743918407633.jpg", "Espa??ol", 505944, true)
    val Estonia = Paises("Estonia", "Tallin", 1319133, "bandera", "Estonio", 45228, true)
    val Finlandia = Paises("Finlandia", "Helsinki", 5528855, "bandera", "Fin??s", 338145, true)
    val Francia = Paises("Francia", "Par??s", 67407241, "Bandera", "Franc??s", 675417, true)
    val Georgia = Paises("Georgia", "Tiflis", 3718200, "bandera", "georgiano", 69700, false)
    val Grecia = Paises("Grecia",  "Atenas", 10607051, "bandera", "Griego", 131957, true)
    val Hungr??a = Paises("Hungr??a", "Budapest", 9769526, "bandera", "H??ngaro", 93028, true)
    val Irlanda= Paises("Irlanda", "Dubl??n", 4857000, "bandera", "Irland??s", 70273, true)
    val Islandia = Paises("Islandia", "Reikiavik", 357050, "bandera", "Island??s", 103000 , false)
    val Italia = Paises("Italia", "Roma", 60257, "bandera", "Italiano", 301340, true)
    val Letonia = Paises("Letonia", "Riga", 1912789, "bandera", "Let??n", 64589, true)
    val Liechtenstein = Paises("Liechtenstein", "Vaduz", 38749, "bandera", "Alem??n", 160, false)
    val Lituania = Paises("Lituania", "Vilna", 2853001, "bandera", "Lituano", 65300, true)
    val Luxemburgo = Paises("Luxemburgo", "Ciudad de Luxemburgo", 626108, "Bandera", "Luxemburgu??s", 2586, true)
    val MacedoniadelNorte = Paises("Macedonia del Norte", "Skopie", 2072531, "bandera", "Macedonio", 25713, false)
    val Malta = Paises("Malta", "La Veleta", 475700, "bandera", "Malt??s", 316, true)
    val Moldavia = Paises("Moldavia", "Chisin??u", 2597100, "Bandera", "Rumano", 33851, false)
    val M??naco = Paises("M??naco", "Ciudad de M??naco", 38100, "Bandera", "Franc??s", 2, true)
    val Montenegro = Paises("Montenegro", "Podgorica", 642550, "bandera", "Montenegrino", 13812, false)
    val Noruega = Paises("Noruega", "Oslo",5425270, "bandera", "Noruego", 323802, false)
    val Pa??sesBajos = Paises("Pa??ses Bajos", "??msterdam", 17302116, "bandera", "Neerland??s", 41543,true)
    val Polonia = Paises("Polonia", "Varsovia", 38208000,"Bandera", "Polaco",312696,true)
    val Portugal= Paises("Portugal", "Lisboa", 10295909, "bandera", "Portugu??s", 92090, true)
    val ReinoUnido = Paises("Reino Unido","Londres", 67747826,"bandera", "Ingl??s", 243610, false )
    val Rep??blicaCheca = Paises("Rep??blica Checa", "Praga", 10610947,"bandera", "Checo", 78867, true)
    val Ruman??a = Paises("Ruman??a", "Bucarest", 19317984, "bandera", "Rumano", 238391, true)
    val Rusia = Paises("Rusia", "Mosc??", 145478097 ,"bandera", "Ruso",17125191 , false)
    val SanMarino = Paises("San Marino", "San Marino",33553 , "bandera", "Italiano", 61,false)
    val Serbia = Paises("Serbia", "Belgrado", 692705, "bandera", "Serbio",  88361, false)
    val Suecia = Paises("Suecia", "Estocolmo", 10343403, "Bandeera", "Sueco", 450295, true, )
    val Suiza = Paises("Suiza", "Berna", 8670000, "Bandera", "Alem??n", 41277, false)
    val Turqu??a = Paises("Turqu??a", "Ankara", 84614562, "bandera", "Turco", 783562, false)
    val Ucrania = Paises("Ucrania", "Kiev", 39510726, "Bandera", "Ucraniano", 603700, false)
    return mutableListOf(
        Albania,
        Alemania,
        Andorra,
        Armenia,
        Austria,
        Azerbaiyan,
        Belgica,
        Bielorrusia,
        BosniayHerzegovina,
        Bulgaria,
        Chipre,
        CiudadVaticano,
        Croacia,
        Dinamarca,
        Eslovaquia,
        Eslovenia,
        Espa??a,
        Estonia,
        Finlandia,
        Francia,
        Georgia,
        Grecia,
        Hungr??a,
        Irlanda,
        Islandia,
        Italia,
        Letonia,
        Liechtenstein,
        Lituania,
        Luxemburgo,
        MacedoniadelNorte,
        Malta,
        Moldavia,
        M??naco,
        Montenegro,
        Noruega,
        Pa??sesBajos,
        Polonia,
        Portugal,
        ReinoUnido,
        Rep??blicaCheca,
        Ruman??a,
        Rusia,
        SanMarino,
        Serbia,
        Suecia,
        Suiza,
        Turqu??a,
        Ucrania
    )
}

fun itsUE(boolean: Boolean): String{
    if (boolean){
        return "S??"
    } else {
        return "No"
    }
}