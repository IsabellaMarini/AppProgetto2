package com.example.appprogetto

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.appprogetto.databinding.ActivityMainBinding
import com.example.appprogetto.databinding.Pagina1RegistratiBinding
import com.example.appprogetto.databinding.Pagina2RegistratiBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Registrati2 : AppCompatActivity() {
    lateinit var binding2: Pagina1RegistratiBinding
    lateinit var binding: Pagina2RegistratiBinding
    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= Pagina2RegistratiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var indietro = findViewById<Button>(R.id.indietro)
        indietro.setOnClickListener() {
            val intent = Intent(this, paginaRegistrati::class.java)
            startActivity(intent)
        }
        val seleziona = findViewById<CalendarView>(R.id.calendarView2)
        val data_nascita = findViewById<TextView>(R.id.dataNascita)
        seleziona.setFirstDayOfWeek(2)
        seleziona.setOnDateChangeListener { calendarView, year, month, day->
            data_nascita.text = (day.toString() + "/" + ((month)+(1)).toString() + "/" + year.toString())
        }

        binding.registrazione.setOnClickListener(){
           val nome= binding2.Nome.text.toString()
           val cognome = binding2.Cognome.text.toString()
           val username = binding2.username.text.toString()
           val email = binding2.email2.text.toString()
           val password = binding2.password2.text.toString()
           val data= binding.dataNascita.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Utenti")
            val Utente = Utenti(nome, cognome,username, email, password, data)
            database.child(username).setValue(Utente).addOnSuccessListener {
                binding2.Nome.text.clear()
                binding2.Cognome.text.clear()
                binding2.username.text.clear()
                binding2.email2.text.clear()
                binding2.password2.text.clear()
                binding.dataNascita.text
                Toast.makeText(this, "Dati salvati correttamente", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "errore", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}