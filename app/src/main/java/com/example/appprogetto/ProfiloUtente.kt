package com.example.appprogetto

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appprogetto.databinding.ActivityProfiloUtenteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ProfiloUtente : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapterUtente: MyAdapterUtente
    private lateinit var users: ArrayList<Users>
    private lateinit var binding: ActivityProfiloUtenteBinding
    private lateinit var user: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    @SuppressLint("ResourceType", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
        binding = ActivityProfiloUtenteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.Utente2
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        users= arrayListOf()
        myAdapterUtente= MyAdapterUtente(users)
        recyclerView.adapter = myAdapterUtente
        user = FirebaseAuth.getInstance()
        db.collection("Users").whereEqualTo("username", intent.getStringExtra("username")).get().addOnSuccessListener {
            documents->
            for(document in documents){
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                users.add(document.toObject(Users::class.java))
            }
            myAdapterUtente.notifyDataSetChanged()
        } .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
        }
    }
}
