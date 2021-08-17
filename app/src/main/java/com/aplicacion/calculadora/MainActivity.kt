package com.aplicacion.calculadora

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var numero1: Double = 0.0
    private var numero2: Double = 0.0
    private var operacionPres: Int = 0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPunto.setOnClickListener {numeroPresionado( "." ) }
        btnCero.setOnClickListener {numeroPresionado( "0" ) }
        btnUno.setOnClickListener {numeroPresionado( "1" ) }
        btnDos.setOnClickListener {numeroPresionado( "2" ) }
        btnTres.setOnClickListener {numeroPresionado( "3" ) }
        btnCuatro.setOnClickListener {numeroPresionado( "4" ) }
        btnCinco.setOnClickListener {numeroPresionado( "5" ) }
        btnSeis.setOnClickListener {numeroPresionado( "6" ) }
        btnSiete.setOnClickListener {numeroPresionado( "7" ) }
        btnOcho.setOnClickListener {numeroPresionado( "8" ) }
        btnNueve.setOnClickListener {numeroPresionado( "9" ) }


        btnSuma.setOnClickListener { operacionPresionada(SUMA)}
        btnResta.setOnClickListener { operacionPresionada(RESTA)}
        btnMultiplicacion.setOnClickListener { operacionPresionada(MULTIPLICACION)}
        btnDivision.setOnClickListener { operacionPresionada(DIVISION)}

        btnClear.setOnClickListener{
            numero1 = 0.0
            numero2 = 0.0
            textResult.text = "0"
            textResult2.text = "0"
            operacionPres = NO_OPERACION
        }

        btnIgual.setOnClickListener{
            val resultado = when(operacionPres){
                SUMA -> numero1 + numero2
                RESTA -> numero1 - numero2
                MULTIPLICACION -> numero1 * numero2
                DIVISION -> numero1 / numero2
                else -> 0
            }
            if(resultado.toDouble().mod(1.0)==0.0){
                if(numero2.mod(1.0)==0.0){
                    textResult2.text = "${textResult2.text}${numero2.toInt()}"
                }else {
                    textResult2.text = "${textResult2.text}$numero2"
                }
                textResult.text = resultado.toInt().toString()
            }else{
                if(numero2.mod(1.0)==0.0){
                    textResult2.text = "${textResult2.text}${numero2.toInt()}"
                }else {
                    textResult2.text = "${textResult2.text}$numero2"
                }
                textResult.text = resultado.toString()
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun numeroPresionado(digito : String){
        if(textResult.text.equals("0")){
            textResult.text = ""
            textResult.text = "${textResult.text}$digito"

        }else{
            textResult.text = "${textResult.text}$digito"

        }
        if(operacionPres == NO_OPERACION){
            numero1 = textResult.text.toString().toDouble()

        }else{
            numero2 = textResult.text.toString().toDouble()

        }
    }

    @SuppressLint("SetTextI18n")
    private fun operacionPresionada(operacion: Int){
        operacionPres = operacion

        numero1 = textResult.text.toString().toDouble()
        val operation = when(operacionPres){
            SUMA -> "+"
            RESTA -> "-"
            MULTIPLICACION -> "*"
            DIVISION -> "/"
            else -> 0
        }
        if(numero1.mod(1.0 )==0.0){
            textResult2.text = numero1.toInt().toString()+operation
        }else{
            textResult2.text = numero1.toString()+operation
        }
        textResult.text = "0"
    }

    companion object{
        const val SUMA = 1
        const val RESTA = 2
        const val MULTIPLICACION = 3
        const val DIVISION = 4
        const val NO_OPERACION = 0
    }
}
