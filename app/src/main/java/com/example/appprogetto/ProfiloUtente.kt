package com.example.appprogetto

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appprogetto.databinding.ActivityProfiloUtenteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProfiloUtente : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapterUtente
    private lateinit var Utente: ArrayList<Users>
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
        Utente= arrayListOf()
        myAdapter= MyAdapterUtente(Utente)
        recyclerView.adapter = myAdapter

        user = FirebaseAuth.getInstance()

    }
}