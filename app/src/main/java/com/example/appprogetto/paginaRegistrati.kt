package com.example.appprogetto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class paginaRegistrati : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina1_registrati)

        var avanti = findViewById<Button>(R.id.avanti)
        avanti.setOnClickListener() {
            val intent = Intent(this, Registrati2::class.java)
            startActivity(intent)
        }
        var indietro = findViewById<Button>(R.id.indietro2)
        indietro.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}