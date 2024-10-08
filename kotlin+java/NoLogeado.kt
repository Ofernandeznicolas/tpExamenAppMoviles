package com.example.examen_app_moviles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import com.google.gson.Gson

import android.util.Patterns
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

class NoLogeado : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(R.layout.no_logeado_layout)

        // Obtengo la instancia sharedpreference para almacenar los datos del usurio
        val AlmacenamientoInterno = getSharedPreferences("infoUsuario", Context.MODE_PRIVATE)


        // Declaro las variables que hacen referencia a los EditTexts y TextViews
        val nameUser = findViewById<EditText>(R.id.nameUser)
        val emailUsar = findViewById<EditText>(R.id.emailUser)
        val ageUser = findViewById<EditText>(R.id.ageUser)

        val textoPantalla = findViewById<TextView>(R.id.textPantalla)

        //Declaro las variables que contienen los Botones
        val btnEnviar = findViewById<Button>(R.id.enviar)
        val btnTerminoCondiciones = findViewById<Button>(R.id.btnTerminos)

        //Declaro las variables que contienen mis CheckBoxs
        val checkTermino = findViewById<CheckBox>(R.id.ChkTerminos)


        //Establezco que se realiza cuando se apreta el Boton Enviar
        btnEnviar.setOnClickListener {

            //Obtengo los valores de los campos y los almaceno en variables al apretar el Boton Enviar
            val nameUser = nameUser.text.toString()
            val emailUser = emailUsar.text.toString()
            val lastNameUser = ageUser.text.toString()

            val mailCorrecto : Boolean = mailValido(emailUser)


            //Creo una condicion para ejecutar lo que quiero si lso campos no estan incompletos
            if (nameUser.isNotEmpty() && mailCorrecto && lastNameUser.isNotEmpty() && checkTermino.isChecked ) {


                // creo una lista que va a contener diccionarios adentro para luego almacenar el historial
                var listHistorialDic = mutableListOf<MutableMap<String, Any?>>()


                // con el bucle for creo hasta 5 diccionarios solamente.
                for (i in 1..5) {
                    var dicHistorial = mutableMapOf<String, Any?>(
                        "fecha" to null,
                        "fechaHora" to null,
                        "ciUno" to null,
                        "tnaUno" to null,
                        "plazoUno" to null,
                        "rendimientoUno" to null,
                        "roiUno" to null,
                        "ciDos" to null,
                        "tnaDos" to null,
                        "plazoDos" to null,
                        "rendimientoDos" to null,
                        "roiDos" to null
                    )

                    listHistorialDic.add(dicHistorial)
                }


                // Utilizo Gson para convertir la lista de diccionarios en una cadena Json para poder almacenar los datos.
                val gson = Gson()
                val historialGson = gson.toJson(listHistorialDic)

                //Guardo los datos de ususario
                AlmacenamientoInterno.edit().apply {

                    putString("Usuario", nameUser)
                    putString("Email", emailUser)
                    putString("Apellido", lastNameUser)
                    putString("ListaHistorial", historialGson)
                    putBoolean("Logueo", true)
                    apply()

                }

                Log.d("Almacenamiento", "datos usuario guardados")

                toTestInversor()

            }else{

                //En caso de no estar los campos completos o tener un dato erroneo informo al usuario.
                var errorDatos : MutableList<String> = mutableListOf()

                if (nameUser.isEmpty()) {errorDatos.add("Nombre")}
                if (lastNameUser.isEmpty()) {errorDatos.add("Apellido")}
                if (!mailCorrecto) {errorDatos.add("Email")}


                if(checkTermino.isChecked){

                }else{
                    Toast.makeText(this, "Debes aceptar los terminos y condiciones para continuar", Toast.LENGTH_SHORT).show()
                }

                if (errorDatos.isNotEmpty()) {

                    if(errorDatos.size > 1) {

                        textoPantalla.text = "Revisa los siguientes campos y completalos de manera correcta : \n ${errorDatos.joinToString(separator = "\n")}"
                    } else {
                        textoPantalla.text = "Revisa el siguiente error y completalo de manera correcta: \n ${errorDatos[0]}"
                    }

                }


                //Toast.makeText(this, "Error en el ingreso de datos", Toast.LENGTH_SHORT).show()

                Log.d("InvalidData","${errorDatos.joinToString(separator = " ")}")


            }


        }


        btnTerminoCondiciones.setOnClickListener {
            toTerminosCondiciones()
        }

    }

    //Funcion que deriva al tag TestInvesor

    private fun toTestInversor() {

        val intent = Intent(this, TestInversor::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab TestInversor")
        finish()
    }

    //Funcion que deriva al tag TerminosCondiciones
    private fun toTerminosCondiciones() {

        val intent = Intent(this, TerminosCondiciones::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab Terminos y condiciones")
        finish()

    }

    //Función para verificar si el mail ingresado es valido (Devuelve un boolean)

    private fun mailValido(email: String): Boolean {

        Log.d("ValidacionEmail", "Se realizo la validacion de email")

        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}
