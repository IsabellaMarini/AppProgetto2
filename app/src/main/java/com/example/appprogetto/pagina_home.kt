package com.example.appprogetto

import android.R
import android.R.layout.simple_expandable_list_item_2
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appprogetto.databinding.ActivityPaginaHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class pagina_home : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private lateinit var notizie : ArrayList<Notizie>
    private lateinit var binding: ActivityPaginaHomeBinding
    private lateinit var user: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Firebase.firestore
        binding = ActivityPaginaHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.notizie
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        notizie= arrayListOf()
        myAdapter= MyAdapter(notizie)
        recyclerView.adapter = myAdapter

        user = FirebaseAuth.getInstance()
        user.currentUser?.uid.let {
            if (it != null) {
                db.collection("Utenti").document(it.toString()).get()
                    .addOnSuccessListener { document ->
                        if (document != null) {
                            Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                            binding.UserNome.text = document.getString("nome")
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "get failed with ", exception)
                    }
            }
        }


            db.collection("Notizie").get().addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    notizie.add(document.toObject(Notizie::class.java))
                }
                myAdapter.notifyDataSetChanged()
            }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents: ", exception)
                }



            binding.disconnetti.setOnClickListener{
                user.signOut()
                startActivity(   Intent(this, MainActivity::class.java)
                )
                finish()
            }
            binding.aggiungi.setOnClickListener{
                startActivity(
                    Intent(this, AggiungiNotizia::class.java)) }
        }}


