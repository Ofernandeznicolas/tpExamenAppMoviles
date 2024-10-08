package com.example.examen_app_moviles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val AlmacenamientoInterno = getSharedPreferences("infoUsuario", Context.MODE_PRIVATE)

        val login = AlmacenamientoInterno.getBoolean("Logueo", false)


        if(login) {

            //si ya se logueo inicio la app con los datos almacenados

            Logeado()

            //  Registro que ingreso a la app con datos almacenados

            Log.d("Estado","Logueado")


        } else {

            // Si no se logueo redirijo al tab para loguearse y almacenar sus datos

            noLogeado()

            //  Registro que ingreso a la app sin datos almacenados

            Log.d("Estado","No logueado")
        }


    }


    //Funcion que deriva al tag de Nologeado

    private fun noLogeado() {

        val intent = Intent(this, NoLogeado::class.java)
        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab NoLogeado")
        finish()

    }

    //Funcion que deriva al tag de Pantalla principal

    private fun Logeado() {
        val intent = Intent(this, PantallaPrincipal::class.java)
        startActivity(intent)
        Log.d("Redireccionamiento", "Se envio al tab Pantalla pricipal")

        finish()
    }

}
