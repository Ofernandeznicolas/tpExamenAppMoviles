package com.example.examen_app_moviles

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PantallaPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.pantalla_principal_layout)

        val textoBienvenido = findViewById<TextView>(R.id.bienvenida)
        val email = findViewById<TextView>(R.id.email)
        val perfilTest = findViewById<TextView>(R.id.perfil)

        val btComparate = findViewById<Button>(R.id.btComparar)
        val btHistory = findViewById<Button>(R.id.btHistorial)
        val btTerminos = findViewById<Button>(R.id.btnTerminos)

        val AlmacenamientoInterno = getSharedPreferences("infoUsuario", Context.MODE_PRIVATE)


        val nombre = AlmacenamientoInterno.getString("Usuario", "nombre")
        val apellido = AlmacenamientoInterno.getString("Apellido", "apellido")
        val mail = AlmacenamientoInterno.getString("Email", "mail")
        val perfil = AlmacenamientoInterno.getString("Test", "perfil Inversor")


        textoBienvenido.text = "Bienvenido $nombre $apellido !!"

        email.text = "Mail : $mail"
        perfilTest.text = "Perfil Inversor : $perfil"


        btComparate.setOnClickListener {

            toComparate()
        }

        btHistory.setOnClickListener {

            toHistorial()
        }

        btTerminos.setOnClickListener {
            toTerminos()
        }

    }

    private fun toComparate() {

        intent = Intent(this, ComparateInversion::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab ComparateInversion")

        finish()

    }

    private fun toHistorial() {

        val intent = Intent(this, Historial::class.java)

        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab Historial")

        finish()
    }

    private fun toTerminos() {
        val intent = Intent (this, TerminosCondiciones::class.java)
        startActivity(intent)

        Log.d("Redireccionamiento", "Se envio al tab TerminosCondiciones")

        finish()
    }


}
