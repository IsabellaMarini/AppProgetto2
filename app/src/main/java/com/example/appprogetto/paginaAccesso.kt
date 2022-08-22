package com.example.appprogetto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.appprogetto.databinding.ActivityMainBinding
import com.example.appprogetto.databinding.PaginaAccessoBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class paginaAccesso : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var bindig : PaginaAccessoBinding
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_accesso)
        val intent : Intent = getIntent()
        var indietro= findViewById<Button>(R.id.indietro3)
        indietro.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var accedi = findViewById<Button>(R.id.Accedi2)
        var username2 = findViewById<EditText>(R.id.email)
        var password2 = findViewById<EditText>(R.id.Password)
        val database:DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
        val User = Utenti()
        

            accedi.setOnClickListener (){
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            }
    }


}