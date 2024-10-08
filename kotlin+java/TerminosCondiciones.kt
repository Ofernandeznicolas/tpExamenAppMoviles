package com.example.examen_app_moviles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class TerminosCondiciones: AppCompatActivity (){
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.terminos_condiciones_layout)

        val AlmacenamientoInterno = getSharedPreferences("infoUsuario", Context.MODE_PRIVATE)
        val login = AlmacenamientoInterno.getBoolean("Logueo", false)


        val textIntroduccion = findViewById<TextView>(R.id.textIntroduccion)
        val textUno = findViewById<TextView>(R.id.textUno)
        val textDos = findViewById<TextView>(R.id.textDos)
        val textTres = findViewById<TextView>(R.id.textTres)
        val textCuatro = findViewById<TextView>(R.id.textCuatro)
        val textCinco = findViewById<TextView>(R.id.textCinco)
        val textSeis = findViewById<TextView>(R.id.textSeis)
        val textSiete = findViewById<TextView>(R.id.textSiete)


        val btnVolver = findViewById<Button>(R.id.btnVolver)


        textIntroduccion.text = "Bienvenido a [CompareInvest]. Al utilizar nuestra aplicación, aceptas cumplir con estos Términos y Condiciones. Si no estás de acuerdo con alguno de estos términos, te recomendamos que no utilices la app."

        textUno.text = "La información proporcionada en [CompareInvest] es únicamente para fines informativos y educativos. No somos agentes financieros ni profesionales en inversiones. La app no debe ser considerada como un consejo financiero, recomendación de inversión o asesoramiento profesional. Siempre se recomienda consultar a un asesor financiero calificado antes de tomar decisiones de inversión."
        textDos.text = "Al utilizar [CompareInvest], reconoces que todas las decisiones de inversión son de tu exclusiva responsabilidad. La app no se hará cargo de las pérdidas o ganancias resultantes de las decisiones tomadas por el usuario. Es fundamental que realices tu propia investigación y análisis antes de invertir."
        textTres.text = "[CompareInvest] no garantiza la precisión, integridad o actualidad de la información disponible en la aplicación. No seremos responsables por ningún daño directo, indirecto, incidental o consecuente que pueda surgir del uso o la incapacidad para usar la app, incluyendo, pero no limitándose a, pérdidas financieras."
        textCuatro.text = "Edad: Debes tener al menos 18 años para utilizar esta aplicación.\n" +
                "Registro: Es posible que debas registrarte y proporcionar información personal para acceder a ciertas funciones.\n" +
                "Comportamiento del Usuario: Te comprometes a utilizar la app de manera responsable y a no realizar actividades ilegales o fraudulentas."
        textCinco.text = "Nos reservamos el derecho a modificar estos Términos y Condiciones en cualquier momento. Cualquier cambio será efectivo inmediatamente después de su publicación en la aplicación. Te recomendamos revisar periódicamente esta sección para estar al tanto de cualquier actualización."
        textSeis.text = "Al utilizar [CompareInvest], reconoces y aceptas que la información proporcionada es únicamente para fines informativos y educativos. [Nombre de la App] no se hace responsable de las decisiones de inversión que tomes basadas en dicha información. No garantizamos la precisión, integridad o actualidad del contenido disponible en la aplicación. En consecuencia, no seremos responsables por ningún daño directo, indirecto o consecuente que pueda resultar del uso de la app o de la información contenida en ella."
        textSiete.text = "Aceptación: Al utilizar [CompareInvest], aceptas cumplir con estos Términos y Condiciones. Si no estás de acuerdo, te recomendamos que no utilices la aplicación.\n" +
                "Registro: Es posible que debas registrarte y proporcionar información personal. Eres responsable de mantener la confidencialidad de tu cuenta y contraseña.\n" +
                "Uso Aceptable: Te comprometes a utilizar la app solo para fines legales y de acuerdo con las leyes aplicables. No debes realizar actividades fraudulentas ni interferir con el funcionamiento de la aplicación.\n" +
                "Modificaciones: Nos reservamos el derecho a modificar o descontinuar cualquier parte de la app en cualquier momento sin previo aviso."


        btnVolver.setOnClickListener {
            if(login) {
                toInicio()
            }else {
                toVolver()
            }

        }

    }


    private fun toVolver() {

        val intent = Intent(this, NoLogeado::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab NoLogeado")

        finish()
    }

    private fun toInicio() {

        val intent = Intent(this, PantallaPrincipal::class.java)

        startActivity(intent)


        Log.d("Redireccionamiento", "Se envio al tab Pantalla pricipal")

        finish()
    }

}
