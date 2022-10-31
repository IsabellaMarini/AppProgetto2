package com.example.appprogetto

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appprogetto.databinding.RegistratiBinding
import com.google.common.io.Files.map
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.HashMap

class paginaRegistrati : AppCompatActivity() {
    lateinit var binding: RegistratiBinding

    lateinit var database : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.firestore
        binding = RegistratiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var indietro = findViewById<Button>(R.id.indietro)
        indietro.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val seleziona = findViewById<CalendarView>(R.id.calendarView3)
        val data_nascita = findViewById<TextView>(R.id.dataNascita)
        seleziona.setFirstDayOfWeek(2)
        seleziona.setOnDateChangeListener { calendarView, year, month, day ->
            data_nascita.text =
                (day.toString() + "/" + ((month) + (1)).toString() + "/" + year.toString())
        }

        binding.registrazione.setOnClickListener() {

            val utente =  hashMapOf(
                "nome" to binding.Name.text.toString(),
                "cognome" to  binding.Surname.text.toString(),
                "username" to binding.Username.text.toString(),
                "email" to binding.Email2.text.toString(),
                "password" to binding.password2.text.toString(),
                "data" to binding.dataNascita.text.toString(),
                "calcio" to binding.calcio.isChecked,
                "tennis" to binding.tennis.isChecked,
                "basket" to  binding.basket.isChecked,
                "nuoto" to binding.nuoto.isChecked,
                "pallavolo" to binding.pallavolo.isChecked,
                "formula uno" to  binding.formula1.isChecked
            )
            var db = database.collection("Utenti")
            db.add(utente as Map<String, Any>).addOnSuccessListener{ documentReference->
                Log.d(TAG, "I dati sono stati salvati correttamente: ${documentReference.id}")
                Toast.makeText(this,
                    "Dati salvati correttamente",
                    Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { e ->
                Log.w(TAG, "Errore", e)
                Toast.makeText(this,
                    "Errore",
                    Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    }


