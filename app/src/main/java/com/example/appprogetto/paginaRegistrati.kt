package com.example.appprogetto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appprogetto.databinding.RegistratiBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class paginaRegistrati : AppCompatActivity() {
    lateinit var binding: RegistratiBinding

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistratiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var indietro = findViewById<Button>(R.id.indietro)
        indietro.setOnClickListener() {
            val intent = Intent(this, paginaRegistrati::class.java)
            startActivity(intent)
        }
        val seleziona = findViewById<CalendarView>(R.id.calendarView3)
        val data_nascita = findViewById<TextView>(R.id.dataNascita)
        seleziona.setFirstDayOfWeek(2)
        seleziona.setOnDateChangeListener { calendarView, year, month, day->
            data_nascita.text = (day.toString() + "/" + ((month)+(1)).toString() + "/" + year.toString())
        }

        binding.registrazione.setOnClickListener(){
            val nome= binding.Name.text.toString()
            val cognome = binding.Surname.text.toString()
            val username = binding.Username.text.toString()
            val email = binding.Email2.text.toString()
            val password = binding.password2.text.toString()
            val data= binding.dataNascita.text.toString()
            val calcio = binding.calcio.isChecked
            val basket = binding.basket.isChecked
            val tennis = binding.tennis.isChecked
            val nuoto = binding.nuoto.isChecked
            val pallavolo = binding.pallavolo.isChecked
            val formula1 = binding.formula1.isChecked
            database = FirebaseDatabase.getInstance().getReference("Utenti")
            val Utente = Utenti(nome, cognome,username, email, password, data, calcio, tennis, basket,
                nuoto, pallavolo, formula1)
            database.child(username).setValue(Utente).addOnSuccessListener {
                binding.Name.text.clear()
                binding.Surname.text.clear()
                binding.Username.text.clear()
                binding.Email2.text.clear()
                binding.password2.text.clear()
                binding.dataNascita.text
                binding.calcio.isChecked
                binding.basket.isChecked
                binding.tennis.isChecked
                binding.nuoto.isChecked
                binding.pallavolo.isChecked
                binding.formula1.isChecked

                Toast.makeText(this, "Dati salvati correttamente", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "errore", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}
