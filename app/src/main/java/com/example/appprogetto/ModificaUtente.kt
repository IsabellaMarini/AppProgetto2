package com.example.appprogetto

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appprogetto.databinding.ActivityModificaUtenteBinding
import com.example.appprogetto.databinding.ActivityProfiloUtenteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ModificaUtente : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapterUtente2: MyAdapterModifica
    private lateinit var users2: ArrayList<Users>
    private lateinit var binding: ActivityModificaUtenteBinding
    private lateinit var user: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    @SuppressLint("ResourceType", "NotifyDataSetChanged", "SetTextI18n")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifica_utente)
        db = Firebase.firestore
        binding = ActivityModificaUtenteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.Utente3
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        users2= arrayListOf()
        myAdapterUtente2= MyAdapterModifica(users2)
        recyclerView.adapter = myAdapterUtente2
        user = FirebaseAuth.getInstance()
        db.collection("Users").whereEqualTo("nome", intent.getStringExtra("nome")).get().addOnSuccessListener {
                documents->
            for(document in documents){
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                users2.add(document.toObject(Users::class.java))
            }
            myAdapterUtente2.notifyDataSetChanged()
        } .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
        }
        binding.indietro5.setOnClickListener {
            val intent = Intent(this, pagina_home::class.java)
            startActivity(intent)
        }






    }
}