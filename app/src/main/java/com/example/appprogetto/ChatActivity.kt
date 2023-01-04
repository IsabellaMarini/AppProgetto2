package com.example.appprogetto

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.appprogetto.databinding.ChatActivityBinding
import com.example.appprogetto.databinding.RegistratiBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatActivity : AppCompatActivity() {
    lateinit var binding: ChatActivityBinding
    lateinit var database : FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ChatActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        mAuth= FirebaseAuth.getInstance()
        database= Firebase.firestore
        binding.inviaMessaggio.setOnClickListener(){
            var messaggio =  hashMapOf(
                "messaggio" to binding.Messaggio.text.toString(),
                "mittente" to  mAuth.currentUser!!.email.toString(),
                "destinatario" to intent.getStringExtra("email2")
            )
            var db = database.collection("Messaggi")
            db.add(messaggio as Map<String, Any>).addOnSuccessListener{ documentReference->
                Log.d(ContentValues.TAG, "I dati sono stati salvati correttamente: ${documentReference.id}")
                Toast.makeText(this,
                    "Dati salvati correttamente",
                    Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Errore", e)
                Toast.makeText(this,
                    "Errore",
                    Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this, pagina_home::class.java)
            startActivity(intent)
        }
    }
}