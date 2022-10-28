package com.example.appprogetto

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appprogetto.databinding.PaginaAccessoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class paginaAccesso : AppCompatActivity() {

    private lateinit var binding:PaginaAccessoBinding
    private lateinit var user: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = PaginaAccessoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()

        checkIfUserIsLogged()


        var indietro = findViewById<Button>(R.id.indietro3)
        indietro.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        binding.Accedi2.setOnClickListener() {
            utenteRegistrato()
        }
    }

    private fun checkIfUserIsLogged(){

        if(user.currentUser != null) {
            startActivity(Intent(this, pagina_home::class.java))
            finish()
        }

    }
    private fun utenteRegistrato(){
        val email = binding.email.text.toString()
        val password = binding.Password.text.toString()

        if(email.isNotEmpty() && password.isNotEmpty()){


             user.signInWithEmailAndPassword(email,password)
                         .addOnCompleteListener{mTask->

                             if(mTask.isSuccessful){
                                 Toast.makeText(this,
                                     "utente collegato",
                                     Toast.LENGTH_SHORT).show()
                                 startActivity(Intent(this, pagina_home::class.java))
                                 finish()

                             } else {
                                 Toast.makeText(this,
                                     "email o password sono errati",
                                     Toast.LENGTH_SHORT).show()
                             }

                         }

        } else{
            Toast.makeText(this,
                "email e password non possono essere vuoti",
                Toast.LENGTH_SHORT).show()
        }
    }
}