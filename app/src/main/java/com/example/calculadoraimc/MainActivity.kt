package com.example.calculadoraimc

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.calculo.Calculo
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraIMCTheme {
                Main()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Main() {

    var peso by remember {
        mutableStateOf("")
    }

    var altura by remember {
        mutableStateOf("")
    }


    val contexto = LocalContext.current
    val mensagem = Calculo()
    var resultadoIMC by remember { mutableStateOf("") }


    Scaffold( //os parâmetros scaffold
        topBar = { // começo da topbar
            TopAppBar(
                backgroundColor = Color(0xFF2196F3),
                contentColor = Color.White,
                title = { Text(text = "Calculadora de IMC") //aparentemente é melhor usar title
                },

                actions = {
                    IconButton(onClick = {
                        peso = ""
                        altura = ""
                        resultadoIMC = ""
                    })
                    {
                        Image(imageVector = ImageVector.vectorResource(id = R.drawable.ic_refresh),
                            contentDescription = "resetar todos os campos" )
                    }

                }
            )

        }

    ) { //aqui começa o conteúdo do scaffold
       Column( //uma única column para a tela inteira
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier
               .fillMaxSize()
               .verticalScroll(rememberScrollState())

       ) { //começo da Column
           Text(
               text = "Calculadora de IMC",
               fontWeight = FontWeight.Bold,
               fontSize = 25.sp,
               color = Color(0xFF2196F3),
               modifier = Modifier.padding(top = 50.dp)
           )

           //textfield 1
          OutlinedTextField(
              value = peso, //lógica do recompose
              onValueChange = {
                  peso = it
              },
              label = {
                  Text(text = "Peso (kg)")
              },

            colors = TextFieldDefaults.outlinedTextFieldColors( //configuração das cores do textfield
                cursorColor = Color(0xFF2196F3),
                focusedBorderColor = Color(0xFF0670C5),
                unfocusedBorderColor = Color(0xFF2196F3),
                focusedLabelColor = Color(0xFF0670C5),
            ),

            textStyle = TextStyle(Color(0xFF0670C5), 18.sp), //configuração do estilo de texto do próprio textfield

            maxLines = 1, //configuração para nao quebrar o textfield
            singleLine = true,


            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 45.dp, top = 35.dp, end = 45.dp),

            keyboardOptions = KeyboardOptions( //definindo o tipo do teclado apenas para número
                keyboardType = KeyboardType.Number
            )
          )

           //textfield 2
           OutlinedTextField(
               value = altura,
               onValueChange = {
                   altura = it
               },
               label = {
                   Text(text = "Altura (cm)")
               },

               colors = TextFieldDefaults.outlinedTextFieldColors(
                   cursorColor = Color(0xFF2196F3),
                   focusedBorderColor = Color(0xFF0670C5),
                   unfocusedBorderColor = Color(0xFF2196F3),
                   focusedLabelColor = Color(0xFF0670C5),
               ),

               textStyle = TextStyle(Color(0xFF0670C5), 18.sp),

               maxLines = 1,
               singleLine = true,


               modifier = Modifier
                   .fillMaxWidth()
                   .padding(start = 45.dp, top = 10.dp, end = 45.dp),

               keyboardOptions = KeyboardOptions(
                   keyboardType = KeyboardType.Number
               )
           )
           
           Button(onClick = { //começo botão
                    if (peso.isEmpty()){
                        Toast.makeText(contexto, "Preencha corretamente o peso", Toast.LENGTH_SHORT).show()
                    }
                    else if(altura.isEmpty()){
                        Toast.makeText(contexto, "Preencha corretamente a altura", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        mensagem.calcular(peso, altura)
                        resultadoIMC = mensagem.resultado
                    }
               },
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(75.dp, 30.dp, 75.dp, 0.dp),

               colors = ButtonDefaults.buttonColors(
                   backgroundColor = Color(0xFF2196F3),
                   contentColor = Color.White
               )
           ) {
               Text(text = "Calcular",
                   fontSize = 20.sp,
                   fontWeight = FontWeight.Bold,

               )
           } //fim botão


            Text(text = resultadoIMC,
                fontSize = 18.sp,
                color = Color(0xFF0670C5),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)

            )
       } // fim da Column
    }
    
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculadoraIMCTheme {
        Main()
    }
}