package com.example.appprogetto

import android.annotation.SuppressLint
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
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


import java.util.*
import java.util.Calendar.YEAR


class paginaRegistrati : AppCompatActivity() {
    lateinit var binding: RegistratiBinding
    lateinit var database : FirebaseFirestore
    lateinit var mAuth: FirebaseAuth
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.firestore
        binding = RegistratiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = Firebase.auth
        var email = binding.Email2.text.toString()!!
        var password = binding.password2.text.toString()!!
        var conferma = binding.password3.text.toString()
        val currentTimestamp = System.currentTimeMillis()
        var indietro = findViewById<Button>(R.id.indietro)
        indietro.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val calendar = Calendar.getInstance()
        calendar.set(YEAR, 2008)
        val seleziona = findViewById<CalendarView>(R.id.calendarView3)
        seleziona.setDate(calendar.getTimeInMillis(), false, false)
        val data_nascita = findViewById<TextView>(R.id.dataNascita)
        seleziona.setFirstDayOfWeek(2)
        seleziona.maxDate= currentTimestamp
        seleziona.setOnDateChangeListener { calendarView, year, month, day ->
            data_nascita.text =
                (day.toString() + "/" + ((month) + (1)).toString() + "/" + year.toString())
        }


        binding.registrazione.setOnClickListener() {
            var utente =  hashMapOf(
                "nome" to binding.Name.text.toString(),
                "cognome" to  binding.Surname.text.toString(),
                "username" to binding.Username.text.toString(),
                "email" to binding.Email2.text.toString(),
                "data" to binding.dataNascita.text.toString(),
            )


            if (binding.Name.text.isEmpty() || binding.Surname.text.isEmpty() || binding.Username.text.isEmpty()
                || binding.Email2.text.isEmpty() || binding.password2.text.isEmpty() || binding.dataNascita.text.isEmpty()){
                Toast.makeText(this,
                    "Dati mancanti",
                    Toast.LENGTH_SHORT).show()}

            else if((binding.password2.text.toString()).length<8){
                Toast.makeText(this,
                    "La password deve contenere almeno 8 caratteri",
                    Toast.LENGTH_SHORT).show()
            }

            else if(!(binding.Email2.text.isEmpty()) || !(binding.password2.text.isEmpty())) {
                mAuth.createUserWithEmailAndPassword(binding.Email2.text.toString(),
                    binding.password2.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val docRef: DocumentReference =
                                database.collection("Users")
                                    .document(binding.Email2.text.toString())
                            docRef.set(utente).addOnSuccessListener {
                                Log.d(TAG, "Profilo utente creato")
                            }
                        } else {
                            Toast.makeText(
                                this, it.exception.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }.addOnFailureListener { e ->
                        Log.w(TAG, "Errore", e)
                        Toast.makeText(
                            this,
                            "Errore",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                var db = database.collection("Users").document(binding.Email2.text.toString())
                db.set(utente as Map<String, Any>).addOnSuccessListener{
                    Toast.makeText(this,
                        "Dati salvati correttamente",
                        Toast.LENGTH_SHORT).show()


                }.addOnFailureListener { e ->
                    Log.w(TAG, "Errore", e)
                    Toast.makeText(this,
                        "Errore",
                        Toast.LENGTH_SHORT).show()
                }
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)}}
}