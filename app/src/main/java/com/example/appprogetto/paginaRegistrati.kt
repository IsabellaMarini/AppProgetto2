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

        val nome = binding.Name.text.toString()
        val cognome = binding.Surname.text.toString()
        val username = binding.Username.text.toString()
        val email = binding.Email2.text.toString()
        val password = binding.password2.text.toString()
        val data = binding.dataNascita.text.toString()
        val calcio = binding.calcio.isChecked
        val basket = binding.basket.isChecked
        val tennis = binding.tennis.isChecked
        val nuoto = binding.nuoto.isChecked
        val pallavolo = binding.pallavolo.isChecked
        val formula1 = binding.formula1.isChecked
        binding.registrazione.setOnClickListener() {

            val utente =  hashMapOf(
                "nome" to nome,
                "cognome" to cognome,
                "username" to username,
                "email" to email,
                "password" to password,
                "data" to data,
                "calcio" to calcio,
                "tennis" to tennis,
                "basket" to  basket,
                "nuoto" to nuoto,
                "pallavolo" to pallavolo,
                "formula uno" to  formula1
            )
            var db = database.collection("Utenti")
            db.add(utente as Map<String, Any>).addOnSuccessListener{ documentReference->
                Log.d(TAG, "I dati sono stati salvati correttamente: ${documentReference.id}")
            }.addOnFailureListener { e ->
                Log.w(TAG, "Errore", e)
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    }


