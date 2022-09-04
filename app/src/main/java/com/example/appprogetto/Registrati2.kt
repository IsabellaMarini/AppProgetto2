package com.example.appprogetto

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Registrati2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina2_registrati)
        val intent: Intent = getIntent()

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

        val nome = findViewById<EditText>(R.id.Nome)
        val cognome = findViewById<EditText>(R.id.Cognome)
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email2)
        val password = findViewById<EditText>(R.id.password2)
        val calcio = findViewById<CheckBox>(R.id.calcio)
        val tennis = findViewById<CheckBox>(R.id.tennis)
        val basket = findViewById<CheckBox>(R.id.basket)
        val nuoto = findViewById<CheckBox>(R.id.nuoto)
        val pallavolo = findViewById<CheckBox>(R.id.pallavolo)
        val formula1 = findViewById<CheckBox>(R.id.formula1)

        var registrati = findViewById<Button>(R.id.registrazione)
        registrati.setOnClickListener() {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}