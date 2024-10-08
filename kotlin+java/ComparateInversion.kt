package com.example.examen_app_moviles

import java.text.SimpleDateFormat
import java.util.Date

import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.widget.EditText

import com.google.gson.Gson

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.reflect.TypeToken

class ComparateInversion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.comparate_inversion_layout)

        val AlmacenamientoInterno = getSharedPreferences("infoUsuario", Context.MODE_PRIVATE)

        val textoUno = findViewById<TextView>(R.id.textoUno)
        val textoDos = findViewById<TextView>(R.id.textoDos)

        val primerCI = findViewById<EditText>(R.id.primerCI)
        val primerTNA = findViewById<EditText>(R.id.primerTNA)
        val primerPlazo = findViewById<EditText>(R.id.primerPlazo)

        val segundoCI = findViewById<EditText>(R.id.segundoCI)
        val segundoTNA = findViewById<EditText>(R.id.segundoTNA)
        val segundoPLazo = findViewById<EditText>(R.id.segundoPlazo)

        val btComparate = findViewById<Button>(R.id.btComparar)
        val btPagPrincipal = findViewById<Button>(R.id.btnPagPricipal)
        val btHistorial = findViewById<Button>(R.id.btnHistorial)

        btPagPrincipal.setOnClickListener {

            toPantallaPrincipal()

        }

        btHistorial.setOnClickListener {
            toHistorial()
        }

        btComparate.setOnClickListener {

            val firstCI = primerCI.text.toString().toFloatOrNull()
            val firstTNA = primerTNA.text.toString().toFloatOrNull()
            val firstPlazo = primerPlazo.text.toString().toIntOrNull()

            val secondCI = segundoCI.text.toString().toFloatOrNull()
            val secondTNA = segundoTNA.text.toString().toFloatOrNull()
            val secondPlazo = segundoPLazo.text.toString().toIntOrNull()

            var primerInversion : Float = 0.0f
            var segundaInversion : Float = 0.0f
            var primerRoi : Float = 0.0f
            var segundoRoi: Float = 0.0f

            if(firstCI != null && firstTNA != null && firstPlazo != null && secondCI != null && secondTNA != null && secondPlazo != null) {

               //Obtener Fecha y hora
               val fechaHora = Date()

               val diaFormato = SimpleDateFormat("dd-MM-yyyy")
               val fechaActual = diaFormato.format(fechaHora)

               val fechaHoraFormato = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
               val fechaHoraActual = fechaHoraFormato.format(fechaHora)


               //Obtener resultado inversiones
               primerInversion = calculoRendimiento(firstCI,firstTNA,firstPlazo)

               segundaInversion = calculoRendimiento(secondCI,secondTNA,secondPlazo)


               val primerBeneficio = primerInversion + firstCI

               primerRoi = calculoRoi(primerBeneficio, firstCI)

               val segundoBeneficio = segundaInversion + secondCI

               segundoRoi = calculoRoi(segundoBeneficio, secondCI)

                
               // Obtengo la lista de diccionarios
               val gson = Gson()
               val tipo = object : TypeToken<MutableList<MutableMap<String, Any?>>>() {}.type

               var listaHistorialJson = AlmacenamientoInterno.getString("ListaHistorial", null)

               var listaHistorial : MutableList<MutableMap<String, Any?>> = gson.fromJson(listaHistorialJson, tipo)



              var fechaMasAntigua : Date? = null

              var contador = 0

              var dicMasAntiguo: MutableMap <String, Any?>? = null

               for (i in listaHistorial.indices) {

                   val dicHistorial = listaHistorial[i]

                   if (dicHistorial["fechaHora"] == null) {

                       dicHistorial["fecha"] = fechaActual
                       dicHistorial["fechaHora"] = fechaHoraActual
                       dicHistorial["ciUno"] = firstCI
                       dicHistorial["tnaUno"] = firstTNA
                       dicHistorial["plazoUno"] = firstPlazo
                       dicHistorial["rendimientoUno"] = primerInversion
                       dicHistorial["roiUno"] = primerRoi
                       dicHistorial["ciDos"] = secondCI
                       dicHistorial["tnaDos"] = secondTNA
                       dicHistorial["plazoDos"] = secondPlazo
                       dicHistorial["rendimientoDos"] = segundaInversion
                       dicHistorial["roiDos"] = segundoRoi

                       break

                   } else {


                       val fecha = dicHistorial["fechaHora"] as String // obtengo string de la fecha del dicionario

                       var fechaComparar : Date = fechaHoraFormato.parse(fecha) // transformo el string a date

                       if(fechaMasAntigua == null) {
                           fechaMasAntigua = fechaComparar
                           dicMasAntiguo = dicHistorial

                       } else {

                           if(fechaComparar.before(fechaMasAntigua)) {
                               fechaMasAntigua = fechaComparar
                               dicMasAntiguo = dicHistorial
                           }

                       }

                       contador = contador + 1

                   }

               }

               if (contador == 5 && dicMasAntiguo != null) {
                   dicMasAntiguo["fecha"] = fechaActual
                   dicMasAntiguo["fechaHora"] = fechaHora
                   dicMasAntiguo["ciUno"] = firstCI
                   dicMasAntiguo["tnaUno"] = firstTNA
                   dicMasAntiguo["plazoUno"] = firstPlazo
                   dicMasAntiguo["rendimientoUno"] = primerInversion
                   dicMasAntiguo["roiUno"] = primerRoi
                   dicMasAntiguo["ciDos"] = secondCI
                   dicMasAntiguo["tnaDos"] = secondTNA
                   dicMasAntiguo["plazoDos"] = secondPlazo
                   dicMasAntiguo["rendimientoDos"] = segundaInversion
                   dicMasAntiguo["roiDos"] = segundoRoi
               }

               val historialGson = gson.toJson(listaHistorial)

               AlmacenamientoInterno.edit().apply {

                   putString("ListaHistorial", historialGson)

                   apply()

                   Log.d("AlmacenamientoInterno","Lista de historial actualizada")
               }

                Log.d("IngresoDatosCorrectos","Se ingresaron los datos para realizar la comparacion")

                toResultadoCalculo(firstCI, firstTNA, firstPlazo, primerInversion, primerRoi, secondCI,secondTNA,secondPlazo,segundaInversion,segundoRoi )


            } else {
                // mensaje si no ingresa los datos
                Toast.makeText(this, "Debes completar de forma correcta todos los campos", Toast.LENGTH_LONG).show()

                Log.d("DatosCalculos","No se ingresaron los datos correctos o faltan campos")
            }


        }
    }

    private fun calculoRendimiento(CI: Float,TNA: Float,Plazo: Int): Float {

        var resultado = (TNA / 360) / 100
        resultado = resultado * CI * Plazo

        Log.d("CalculoRendimiento", "Se realizo CalculoRendimiento")
        return resultado

    }

    private fun calculoRoi(beneficio: Float, CI: Float): Float {

        var roi = (beneficio - CI) / CI
        roi = roi * 100

        Log.d("CalculoRoi", "Se realizo calculoRoi")

        return roi

    }

    private fun toHistorial() {

        val intent = Intent(this, Historial::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab Historial")

        finish()
    }

    private fun toPantallaPrincipal() {

        val intent = Intent(this, PantallaPrincipal::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab Pantalla pricipal")

        finish()
    }


    //tengo que llevarme los datos

    private fun toResultadoCalculo(ciUno: Float, tnaUno: Float, plazoUno: Int, rendimientoUno: Float, roiUno: Float,
                                   ciDos: Float, tnaDos: Float, plazoDos: Int, rendimientoDos: Float, roiDos: Float) {

        val intent = Intent(this, ResultadoCalculo::class.java).apply {

            putExtra("ciUno", ciUno)
            putExtra("tnaUno", tnaUno)
            putExtra("plazoUno", plazoUno)
            putExtra("rendimientoUno", rendimientoUno)
            putExtra("roiUno", roiUno)

            putExtra("ciDos", ciDos)
            putExtra("tnaDos", tnaDos)
            putExtra("plazoDos", plazoDos)
            putExtra("rendimientoDos", rendimientoDos)
            putExtra("roiDos", roiDos)


        }


        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab ResultadoCalculo")

        finish()
    }
}
