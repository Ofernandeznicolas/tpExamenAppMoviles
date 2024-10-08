package com.example.examen_app_moviles


import android.content.Context
import android.content.Intent
import android.widget.TextView

import com.google.gson.Gson

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.reflect.TypeToken

class Historial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.historial_layout)


        val btPrincipal = findViewById<Button>(R.id.btnPagPrincipal)
        val btComparar = findViewById<Button>(R.id.btnComparar)

        val texto = findViewById<TextView>(R.id.historial1)

        val texto2 = findViewById<TextView>(R.id.registro1)

        val texto3 = findViewById<TextView>(R.id.registro2)


        val AlmacenamientoInterno = getSharedPreferences("infoUsuario", Context.MODE_PRIVATE)

        val gson = Gson()

        val tipo = object : TypeToken<MutableList<MutableMap<String, Any?>>>() {}.type

        var listaHistorialJson = AlmacenamientoInterno.getString("ListaHistorial", null)

        var listaHistorial : MutableList<MutableMap<String, Any?>> = gson.fromJson(listaHistorialJson, tipo)

        val textoAcumulado = StringBuilder()
        val textoAcumuladoDos = StringBuilder()

        val diccionarioVacio = listaHistorial.all { it["fecha"] == null }


        if (!diccionarioVacio){

            for (i in listaHistorial.indices) {

                val dicHistorial = listaHistorial[i]


                if (dicHistorial["fecha"] != null ) {

                    textoAcumulado.append("Fecha ${dicHistorial["fecha"]} : \n" + "\n")
                        .append("Capital Inicial: $ ${dicHistorial["ciUno"]}\n")
                        .append("TNA: ${dicHistorial["tnaUno"]} % \n")
                        .append("Plazo: ${dicHistorial["plazoUno"]} días \n")
                        .append("Rendimiento: $ ${dicHistorial["rendimientoUno"]}\n")
                        .append("Roi: ${dicHistorial["roiUno"]} % \n" + "\n" + "\n" + "\n")


                    textoAcumuladoDos.append(""+"\n" +"\n")
                        .append("Capital Inicial: $ ${dicHistorial["ciDos"]}\n")
                        .append("TNA: ${dicHistorial["tnaDos"]} % \n")
                        .append("Plazo: ${dicHistorial["plazoDos"]} días \n")
                        .append("Rendimiento: $ ${dicHistorial["rendimientoDos"]}\n")
                        .append("Roi: ${dicHistorial["roiDos"]} % \n" + "\n"+ "\n" + "\n")

                }
            }

            texto2.text = textoAcumulado.toString()

            texto3.text = textoAcumuladoDos.toString()

        } else {
            texto.text = "No tenes registros almacenados"
            texto2.visibility = View.GONE
            texto3.visibility = View.GONE
        }




        btPrincipal.setOnClickListener {
            toPagPrincipal()
        }

        btComparar.setOnClickListener {
            toComparar()
        }
    }



    private fun toPagPrincipal() {

        val intent = Intent(this, PantallaPrincipal::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab Pantalla pricipal")

        finish()
    }

    private fun toComparar() {
        val intent = Intent(this, ComparateInversion::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab ComparateInversion")

        finish()

    }
}
