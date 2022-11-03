package com.example.appprogetto

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.appprogetto.databinding.ActivityPaginaHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class pagina_home : AppCompatActivity() {

    private lateinit var binding: ActivityPaginaHomeBinding
    private lateinit var user:FirebaseAuth
    private lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
        binding = ActivityPaginaHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = FirebaseAuth.getInstance()
        if(user.currentUser != null) {
            user.currentUser?.let {
                binding.UserEmail.text = it.email
            }
        }

        var notizie= db.collection("Notizie").get().addOnSuccessListener {    documents ->
            for (document in documents) {
                Log.d(TAG, "${document.id} => ${document.data}")
            }
        }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
        }

        binding.notizia.text = notizie.toString()

        binding.disconnetti.setOnClickListener{
            user.signOut()
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        }
    }
}