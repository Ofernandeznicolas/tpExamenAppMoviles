package com.example.examen_app_moviles

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ResultadoCalculo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.resultado_calculo_layout)

        val textoCiUno = findViewById<TextView>(R.id.ciUno)
        val textoTnaUno = findViewById<TextView>(R.id.tnaUno)
        val textoPlazoUno = findViewById<TextView>(R.id.plazoUno)
        val textoRendimientoUno = findViewById<TextView>(R.id.rendimiendoUno)
        val textoRoiUno = findViewById<TextView>(R.id.roiUno)
        val textoCfUno = findViewById<TextView>(R.id.cfUno)


        val textoCiDos = findViewById<TextView>(R.id.ciDos)
        val textoTnaDos = findViewById<TextView>(R.id.tnaDos)
        val textoPlazoDos = findViewById<TextView>(R.id.plazoDos)
        val textoRendimientoDos = findViewById<TextView>(R.id.rendimiendoDos)
        val textoRoiDos = findViewById<TextView>(R.id.roiDos)
        val textoCfDos = findViewById<TextView>(R.id.cfDos)


        val textoRecomendacion = findViewById<TextView>(R.id.recomendacion)

        val btnHistorial = findViewById<Button>(R.id.btnHistorial)
        val btnComparar = findViewById<Button>(R.id.btonComparar)
        val btnInicio = findViewById<Button>(R.id.btnInicio)


        val ciUno = intent.getFloatExtra("ciUno", 0.0f)
        val tnaUno = intent.getFloatExtra("tnaUno", 0.0f)
        val plazoUno = intent.getIntExtra("plazoUno", 0)
        val rendimientoUno = intent.getFloatExtra("rendimientoUno", 0.0f)
        val roiUno = intent.getFloatExtra("roiUno", 0.0f)

        val cfUno = ciUno + rendimientoUno


        val ciDos = intent.getFloatExtra("ciDos",0.0f)
        val tnaDos = intent.getFloatExtra("tnaDos",0.0f)
        val plazoDos = intent.getIntExtra("plazoDos", 0)
        val rendimientoDos = intent.getFloatExtra("rendimientoDos",0.0f)
        val roiDos = intent.getFloatExtra("roiDos",0.0f)

        val cfDos = ciDos + rendimientoDos



        textoCiUno.text = "Capital Inicial : $ $ciUno"
        textoTnaUno.text = "TNA: $tnaUno %"
        textoPlazoUno.text = "En un plazo de $plazoUno días"
        textoRendimientoUno.text = "El  rendimiento es de $ $rendimientoUno"
        textoRoiUno.text = "Su roi es del $roiUno % "
        textoCfUno.text = "Capital final : $ $cfUno"

        textoCiDos.text = "Capital Inicial : $ $ciDos"
        textoTnaDos.text = "TNA: $tnaDos %"
        textoPlazoDos.text = "En un plazo de $plazoDos días"
        textoRendimientoDos.text = "El  rendimiento es de $ $rendimientoDos"
        textoRoiDos.text = "Su roi es del $roiDos % "
        textoCfDos.text = "Capital final : $ $cfDos"


        if(roiUno > roiDos) {
            textoRecomendacion.text = "La primera inversión se recomienda más ya que el Roi es mayor, por lo tanto su ganancia sera mayor"
        }
        if (roiUno< roiDos){
            textoRecomendacion.text = "La segunda inversión se recomienda más ya que el Roi es mayor, por lo tanto su ganancia sera mayor"
        }
        if (roiUno == roiDos){
            textoRecomendacion.text = "Se recomienda cualquiera de las 2 inversiones ya que su Roi es equivalente, por lo tanto la ganancia sera igual"
        }



        btnInicio.setOnClickListener {
            toInicio()
        }

        btnComparar.setOnClickListener {
            toComparar()
        }

        btnHistorial.setOnClickListener {
            toHistorial()
        }
    }




    private fun toHistorial() {

        val intent = Intent(this, Historial::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab Historial")

        finish()
    }

    private fun toComparar() {

        val intent = Intent(this, ComparateInversion::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab ComparateInversion")

        finish()
    }


    private fun toInicio() {

        val intent = Intent(this, PantallaPrincipal::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab Pantalla pricipal")

        finish()
    }
}
