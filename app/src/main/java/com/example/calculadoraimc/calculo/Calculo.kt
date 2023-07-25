package com.example.calculadoraimc.calculo

import androidx.compose.runtime.Composable
import java.text.DecimalFormat

class Calculo {

    var resultado = ""
    private var resultadoIMC = ""

    fun calcular(peso: String, altura: String ){
        val pesoConvertido = peso.toDouble()
        val alturaConvertida = altura.toDouble()


        val imc = pesoConvertido / (alturaConvertida * alturaConvertida)
        val decimalFormat = DecimalFormat("0.00") //para formatar as casas decimais é necessário criar essa variavel ai

        if (imc <= 16.9){
            resultado = "Muito abaixo do peso! \n IMC: ${decimalFormat.format(imc)}" //formatação das casas decimais com a variavel criada acima
        }
        else if (imc <= 18.4){
            resultado = "Abaixo do peso, coma! \n IMC: ${decimalFormat.format(imc)}"
        }
        else if (imc <= 24.9){
            resultado = "Peso normal, parabainsss!! \n IMC: ${decimalFormat.format(imc)}"
        }
        else if (imc <= 29.9){
            resultado = "Acima do peso :( \n IMC: ${decimalFormat.format(imc)}"
        }
        else if (imc <= 34.9){
            resultado = "Obesidade de grau I :( \n IMC: ${decimalFormat.format(imc)}"
        }
        else if (imc <= 40){
            resultado = "Obesidade de grau II, infelizmente. \n IMC: ${decimalFormat.format(imc)}"
        }
        else if (imc > 40){
            resultado = "Obesidade de grau III, procure o médico!. \n IMC: ${decimalFormat.format(imc)}"
        }

        resultadoIMC = resultado
    }

    fun exibirmensagem():String{
        return resultadoIMC
    }
}