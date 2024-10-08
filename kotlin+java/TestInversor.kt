package com.example.examen_app_moviles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class TestInversor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.test_inversor_layout)

        val AlmacenamientoInterno = getSharedPreferences("infoUsuario", Context.MODE_PRIVATE)

        val rbEdad = findViewById<RadioGroup>(R.id.opcionEdad)
        val rbExperiencia = findViewById<RadioGroup>(R.id.opcionExperiencia)
        val rbPorcentajeMensual = findViewById<RadioGroup>(R.id.porcentajeMensual)

        val btTest = findViewById<Button>(R.id.btTest)
        val btSaveContinue = findViewById<Button>(R.id.btSaveContinue)

        var testFinal = ""

        btTest.setOnClickListener {

            val rbEdadSelect = rbEdad.checkedRadioButtonId
            val rbExperienciaSelect = rbExperiencia.checkedRadioButtonId
            val rbPorcentajeMensualSelect = rbPorcentajeMensual.checkedRadioButtonId


            if (rbEdadSelect != -1 && rbExperienciaSelect != -1 && rbPorcentajeMensualSelect != -1) {

                val rbEdadSelect = findViewById<RadioButton>(rbEdadSelect)
                val rbExperienciaSelect = findViewById<RadioButton>(rbExperienciaSelect)
                val rbPorcentajeMensualSelect = findViewById<RadioButton>(rbPorcentajeMensualSelect)

                val preguntaUno = rbEdadSelect.tag
                val preguntaDos = rbExperienciaSelect.tag
                val preguntaTres = rbPorcentajeMensualSelect.tag




                var resultadoTest = listOf(preguntaUno,preguntaDos,preguntaTres)


                if(resultadoTest[2] == "3a"){
                    //conservador

                    testFinal = "Conservador"
                }

                if(resultadoTest[2] == "3d") {
                    //agresivo
                    testFinal = "Agresivo"
                }

                if(resultadoTest[2] == "3b") {

                    if(resultadoTest[0] == "1c" || resultadoTest[0] == "1d" && resultadoTest[1] == "2a" || resultadoTest[1] == "2b") {

                        //conservador

                        testFinal = "Conservador"

                    }else {
                        //Moderado

                        testFinal = "Moderado"
                    }
                }

                if(resultadoTest[2] == "3c") {

                    if(resultadoTest[1] == "2c" || resultadoTest[1] == "2d") {

                        // Agresivo

                        testFinal = "Agresivo"

                    } else {

                        // Moderado

                        testFinal = "Moderado"
                    }
                }


                val resultado = findViewById<TextView>(R.id.ResultadO)

                resultado.text = "$testFinal"

            }else{
                //campos no completos

                val resultado = findViewById<TextView>(R.id.ResultadO)

                resultado.text = "Por favor responde todas las preguntas para continuar"
            }


        }

        btSaveContinue.setOnClickListener {
            // Verificar que guardo por primera vez el perfil

            if(testFinal.isNotEmpty()) {
                AlmacenamientoInterno.edit().apply {

                    putString("Test", testFinal)
                    apply()
                }

                toComparateInversion()

            }else{

                val resultado = findViewById<TextView>(R.id.ResultadO)

                resultado.text = "Para continuar debe completar el test de inversor por primera vez"

            }


        }


    }

    private fun toComparateInversion() {

        val intent = Intent(this, ComparateInversion::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab ComparateInversion")

        finish()
    }

}
