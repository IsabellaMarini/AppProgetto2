package com.example.appprogetto


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var accedi = findViewById<Button>(R.id.Accedi)
        accedi.setOnClickListener(){
                val intent = Intent(this, paginaAccesso::class.java)
                startActivity(intent)
            }

        var registrati = findViewById<Button>(R.id.Registrati)
        registrati.setOnClickListener(){
            val intent = Intent(this, paginaRegistrati::class.java)
            startActivity(intent)
         }
        }
    }

